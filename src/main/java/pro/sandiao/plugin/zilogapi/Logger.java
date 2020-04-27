package pro.sandiao.plugin.zilogapi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;

public class Logger {

    private PrintStream ps;
    private String filePath;
    private String dateFormat;
    private String linker = " -> ";

    /**
     * 如果文件不存在会创建文件 同时会设置日期格式
     * 会按照设置的字符集来输出
     * @author msg_dw
     * @param file 文件
     * @param dateFormat 日期格式
     * @param csn 字符集
     */
    public Logger(File f, String dateFormat, String csn) {
        try {
            File pf = f.getParentFile();
            if (!pf.exists()) pf.mkdirs();
            if (!f.exists()) f.createNewFile();
            filePath = f.getPath();
            setLogDateFormat(dateFormat);
            ps = new PrintStream(new FileOutputStream(f, true), true, csn);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 如果文件不存在会创建文件 同时会设置日期格式
     * 会按照设置的字符集来输出
     * @author msg_dw
     * @param fileName 文件名
     * @param dateFormat 日期格式
     * @param csn 字符集
     */
    public Logger(String fileName, String dateFormat, String csn) {
        this(new File(fileName), dateFormat, csn);
    }
    
    /**
     * 如果文件不存在会创建文件 同时会设置日期格式
     * 默认使用UTF-8编码
     * @author msg_dw
     * @param file       文件
     * @param dateFormat 日期格式
     */
    public Logger(File f, String dateFormat) {
        this(f, dateFormat, "UTF-8");
    }

    /**
     * 如果文件不存在会创建文件 同时会设置日期格式
     * 默认使用UTF-8编码
     * @author msg_dw
     * @param fileName   文件名
     * @param dateFormat 日期格式
     */
    public Logger(String fileName, String dateFormat) {
        this(new File(fileName), dateFormat);
    }

    /**
     * 如果文件不存在会创建文件
     * 默认使用UTF-8编码
     * @author msg_dw
     * @param file 文件
     */
    public Logger(File f) {
        this(f, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 如果文件不存在会创建文件
     * 默认使用UTF-8编码
     * @author msg_dw
     * @param fileName 文件名
     */
    public Logger(String fileName) {
        this(new File(fileName));
    }

    /**
     * 向文件末尾写入字符串 不带任何格式
     * @author msg_dw
     * @param str 写入的数据
     */
    public void write(String str) {
        ps.println(str);
    }

    /**
     * 会自动调用对象的toString方法 方便写入一些其它数据
     * 自己写方法加格式
     * @author msg_dw
     * @param obj 写入的对象
     */
    public void write(Object obj){
        ps.println(obj);
    }

    /**
     * 向文件末尾写入日志信息 带有时间格式
     * @author msg_dw
     * @param str 记录的信息
     */
    public void log(String str){
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        write(df.format(System.currentTimeMillis()) + linker + str);
    }

    /**
     * 开启一个线程 记录日志
     * 可能会导致记录顺序不同
     * @author msg_dw
     * @param str 记录的信息
     */
    public void startThreadLog(String str){
        new Thread(() -> log(str)).start();
    }

    /**
     * @author msg_dw
     * @return 日志文件保存的文件路径
     */
    public String getLogFilePath(){
        return filePath;
    }

    /**
     * 设置记录日志的时间格式
     * @author msg_dw
     * @param dateFormat 时间格式
     */
    public void setLogDateFormat(String dateFormat){
        this.dateFormat = dateFormat;
    }

    /**
     * @author msg_dw
     * @return 返回日志的时间格式
     */
    public String getLogDateFormat(){
        return dateFormat;
    }

    /**
     * 设置时间与日志内容的连接符
     * 你也可以直接用write()写入完整文本
     * @author msg_dw
     * @param linker 连接符
     */
    public void setLogLinker(String linker){
        this.linker = linker;
    }

    /**
     * @author msg_dw
     * @return 返回连接符
     */
    public String getLogLinker(){
        return linker;
    }

    /**
     * 关闭流
     * 关闭流之后 你不能在记录日志
     * 关闭流之后才能移除文件
     * @author msg_dw
     */
    public void close(){
        ps.close();
    }
}