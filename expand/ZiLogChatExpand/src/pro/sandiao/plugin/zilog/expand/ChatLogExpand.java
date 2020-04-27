package pro.sandiao.plugin.zilog.expand;

import java.io.File;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import pro.sandiao.plugin.zilogapi.LogExpand;
import pro.sandiao.plugin.zilogapi.LoggerUtils;
import pro.sandiao.plugin.zilogapi.Main;

//扩展到 LogExpand 
public class ChatLogExpand extends LogExpand{

	//入口 可以在这里执行操作
	@Override
	public void regExpand() {
		//入口
	}
	
	//返回扩展作者
	@Override
	public String getAuthor() {
		return "msg_dw";
	}

	//返回扩展名
	@Override
	public String getExpandName() {
		return "ChatLogExpand";
	}

	//返回版本号
	@Override
	public String getVersionString() {
		return "1.0.0";
	}
	
	//这里不需要注册事件 直接写就好
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {	//聊天事件是异步的 直接记录就好
		//这里获取插件文件夹 这个可以获取到ZiLogAPI的文件夹 如果用相对路径的话 根目录是服务器的根目录
		File pluginFolder = Main.getMe().getDataFolder();
		//玩家名
		String pname = e.getPlayer().getName();
		//这里静态方法获取工具类实例
		LoggerUtils lu = LoggerUtils.getInstance();
		//这里有四个重载方法 自己摸索
		lu.log(
			//写入的文件 这里的路径是plugin/ZiCore/ZiLogAPI/log/chat/玩家名.yml
			new File(pluginFolder, "log/chat/" + pname + ".yml"), //写入日志 建议用.yml后缀 避免被垃圾侠误杀
			//写入的内容 这里是 [日期 时间] 玩家名说: 聊天信息
			lu.getDate("[yyyy-MM-DD HH:mm:ss] ") + pname + "说: " + e.getMessage()
		);
	}
}
