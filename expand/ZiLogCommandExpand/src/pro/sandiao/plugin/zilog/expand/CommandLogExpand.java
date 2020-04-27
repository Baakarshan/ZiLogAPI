package pro.sandiao.plugin.zilog.expand;

import java.io.File;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import pro.sandiao.plugin.zilogapi.LogExpand;
import pro.sandiao.plugin.zilogapi.Main;
import pro.sandiao.plugin.zilogapi.Logger;

//扩展到 LogExpand 
public class CommandLogExpand extends LogExpand{
	
	private Logger Logger;

	//入口 可以在这里执行操作
	@Override
	public void regExpand() {
		//实例化 记录类 这种方式必须要通过 Logger.close() 关闭之后 才能修改文件 不然会被占用
		Logger = new Logger(new File(Main.getMe().getDataFolder(),"log/command/cmds.yml"));
	}
	
	//返回扩展作者
	@Override
	public String getAuthor() {
		return "msg_dw";
	}

	//返回扩展名
	@Override
	public String getExpandName() {
		return "CommandLogExpand";
	}

	//返回版本号
	@Override
	public String getVersionString() {
		return "1.0.0";
	}
	
	//这里不需要注册事件 直接写就好
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		//Logger会自动加上时间和分隔符
		//Logger.setLogDateFormat("日期格式"); 设置日期格式
		//Logger.setLogLinker("分隔符");		设置分隔符
		//Logger.write("字符串/对象");	写入原始数据 自己去排版 
		//其他的方法自己翻源码找
		Logger.log(e.getPlayer().getName() + "执行了: " + e.getMessage());
	}
}
