package pro.sandiao.plugin.zilogapi;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class LogExpandLoader {
    public void loadExpands() throws Exception {
        ConsoleCommandSender ccs = Bukkit.getConsoleSender();
        ccs.sendMessage("[ZiLogAPI] 开始加载扩展...");
        File fs = new File(Main.getMe().getDataFolder(), "expand");
        if (!fs.exists()) fs.mkdirs();

        for (File f : fs.listFiles()){
            if (f.isDirectory()) continue;
            String fn = f.getName();
            if (fn.endsWith(".jar") || fn.endsWith(".zi") || fn.endsWith(".ex") ) {
                URL url = f.toURI().toURL();
                URLClassLoader classLoader = new URLClassLoader(new URL[] { url }, Main.class.getClassLoader());

                JarFile jar = new JarFile(f);
                Enumeration<JarEntry> jes = jar.entries();
                while (jes.hasMoreElements()){
                    JarEntry je = jes.nextElement();
                    String cn = je.getName();
                    if (cn.endsWith(".class")) {
                        cn = cn.substring(0, cn.lastIndexOf(".class")).replaceAll("/", ".");
                        Class<?> c = classLoader.loadClass(cn);
                        try {
                            Class<? extends LogExpand> led = c.asSubclass(LogExpand.class);
                            LogExpand le = led.newInstance();
                            if (le.canRegister()){
                                le.registerExpand();

                                ccs.sendMessage("-----------[ZiLogAPI]-----------");
                                ccs.sendMessage("["+le.getExpandName()+"] 扩展已加载");
                                ccs.sendMessage("["+le.getExpandName()+"] 作者: " + le.getAuthor());
                                ccs.sendMessage("["+le.getExpandName()+"] 版本号: " + le.getVersionString());
                                ccs.sendMessage("--------------------------------");
                            }
                        } catch (ClassCastException e) {
                            
                        }
                    }
                }
                jar.close();
                classLoader.close();
            }
        }
        ccs.sendMessage("[ZiLogAPI] 扩展加载完毕");
    }
}