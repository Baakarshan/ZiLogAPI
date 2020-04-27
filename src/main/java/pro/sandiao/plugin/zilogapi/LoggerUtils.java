package pro.sandiao.plugin.zilogapi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;

public class LoggerUtils {
 
    private static LoggerUtils instance;

    private LoggerUtils() {}

    /**
     * 你不能实例化这个类 只能通过这个静态方法获取实例
     * @return 获取日志实例
     */
    public static LoggerUtils getInstance(){
        if (instance != null) return instance;
        else return new LoggerUtils();
    }


    /**
     * 向文件写入一行字符 流会自动关闭
     * @author msg_dw
     * @param f 文件
     * @param str 字符串
     * @param csn 字符集
     */
    public void log(File f, String str, String csn){
        PrintStream ps = null;
        try {
            File pf = f.getParentFile();
            if (!pf.exists()) pf.mkdirs();
            if (!f.exists()) f.createNewFile();
            ps = new PrintStream(new FileOutputStream(f, true), true, csn);
            ps.println(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (ps != null) ps.close();
        }
    }

    /**
     * 向文件写入一行字符串 流会自动关闭
     * @author msg_dw
     * @param fileName 文件名
     * @param str 字符串
     * @param csn 字符集
     */
    public void log(String fileName, String str, String csn){
        log(new File(fileName), str, csn);
    }

    /**
     * 向文件写入一行字符串 流会自动关闭
     * 默认使用UTF-8编码
     * @author msg_dw
     * @param f 文件
     * @param str 字符串
     */
    public void log(File f, String str){
        log(f, str, "UTF-8");
    }

    /**
     * 向文件写入一行字符串 流会自动关闭
     * 默认使用UTF-8编码
     * @author msg_dw
     * @param fileName 文件名
     * @param str 字符串
     */
    public void log(String fileName, String str){
        log(new File(fileName), str);
    }

    /**
     * 获取当前时间的格式化文本
     * @author msg_dw
     * @param dateFormat 时间格式
     * @return 当前时间的格式化文本
     */
    public String getDate(String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(System.currentTimeMillis());
    } 
}