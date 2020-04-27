package pro.sandiao.plugin.zilog.expand;

import java.io.File;

import org.black_ixx.playerpoints.event.PlayerPointsChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;

import org.black_ixx.playerpoints.PlayerPoints;
import pro.sandiao.plugin.zilogapi.LogExpand;
import pro.sandiao.plugin.zilogapi.LoggerUtils;
import pro.sandiao.plugin.zilogapi.Main;

//扩展到 LogExpand 
public class PointsLogExpand extends LogExpand{

	//入口 可以在这里执行操作 你可以调用其它插件的api 甚至更多的扩展性
	@Override
	public void regExpand() {
		//入口可以写一些其它东西 比如说读取配置这类的 
		Bukkit.getConsoleSender().sendMessage("["+getExpandName()+"] " + "by: msg_dw");
	}
	
	//返回是否能注册 干嘛的呢 判断前置插件存不存在
	@Override
	public boolean canRegister() {
		//找插件 找不到就返回 null 这样子 如果插件存在 就返回true 能注册
		return Bukkit.getPluginManager().getPlugin("PlayerPoints") != null;
	}
	
	//返回扩展作者
	@Override
	public String getAuthor() {
		return "msg_dw";
	}

	//返回扩展名
	@Override
	public String getExpandName() {
		return "PointsLogExpand";
	}

	//返回版本号
	@Override
	public String getVersionString() {
		return "1.0.0";
	}
	
	//这里不需要注册事件 直接写就好
	@EventHandler			//更多的扩展性
	public void onPoint(PlayerPointsChangeEvent e) {	//这里用点券插件的事件(取消事件不发点券哦)
		//获取插件文件夹
		File pluginFolder = Main.getMe().getDataFolder();
		//获取文件名
		String pname = Bukkit.getOfflinePlayer(e.getPlayerId()).getName();
		//获取增加减少
		int a = e.getChange();
		String str = a < 0 ? "减少了" : "增加了";	
		a = a < 0 ? -a : a;	//获取绝对值
		//获取实例
		LoggerUtils lu = LoggerUtils.getInstance();
		//获取当前点券数量
		int point = e.getChange() + PlayerPoints.class.cast(Bukkit.getPluginManager().getPlugin("PlayerPoints")).getAPI().look(e.getPlayerId());
		//记录信息
		lu.log(
			new File(pluginFolder, "log/point/points.yml"),	//数据量不多 全存一起
			lu.getDate("[yyyy-MM-DD HH:mm:ss] ") + pname + "的点券" + str + a + "\t玩家剩余点券: " + point	//信息
		);
	}
}
