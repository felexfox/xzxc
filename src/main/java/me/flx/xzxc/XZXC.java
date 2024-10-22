package me.flx.xzxc;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class XZXC extends JavaPlugin {

    public static XZXC instance;

    public static XZXC getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new OldWorldListener(), this);
        new LimboGenerator();
    }

    @Override
    public void onDisable(){
        // эта хуйня когда-то пригодится для рецептов, хд
    }
}
