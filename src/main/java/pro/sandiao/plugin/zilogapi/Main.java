package pro.sandiao.plugin.zilogapi;

import org.bukkit.plugin.Plugin;

import pro.sandiao.plugin.zicore.api.ZiPlugin;

public class Main extends ZiPlugin {

    private static Plugin me;

    public static Plugin getMe() {
        return me;
    }

    @Override
    public void disable() {
    }

    @Override
    public void enable() {
        me = this;
        try {
            new LogExpandLoader().loadExpands();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}