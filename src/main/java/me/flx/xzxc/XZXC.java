package me.flx.xzxc;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class XZXC extends JavaPlugin {

    private static XZXC instance;

    @Override
    public void onEnable() {
        // сосиски
    }

    public static XZXC getPlugin() {
        return instance;
    }

    @Override
    public void onDisable(){
        // эта хуйня когда-то пригодится для рецептов, хд
    }
}
