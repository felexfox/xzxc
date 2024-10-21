package me.flx.xzxc;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class XZXC extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new OldWorldListener(), this);
    }

    @Override
    public void onDisable(){
        // эта хуйня когда-то пригодится для рецептов, хд
    }
}
