package pro.sandiao.plugin.zilogapi;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public abstract class LogExpand implements Listener {

    public void registerExpand(){
        Bukkit.getPluginManager().registerEvents(this, Main.getMe());
        regExpand();
    }

    public boolean canRegister(){return true;}

    public abstract void regExpand();

    public abstract String getExpandName();

    public abstract String getAuthor();

    public abstract String getVersionString();
}
