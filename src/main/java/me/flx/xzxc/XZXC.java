package me.flx.xzxc;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public final class XZXC extends JavaPlugin {

    private final Artifacts artifacts = new Artifacts(this);

    public static XZXC instance;

    public static XZXC getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        artifacts.RegisterRecipes();

        Artifacts customItems = new Artifacts(this);
        CustomGiveCommand giveCommand = new CustomGiveCommand(customItems);

        Bukkit.getPluginManager().registerEvents(new OldWorldListener(this), this);
        AshParticleEffect ashParticleEffect = new AshParticleEffect(this);
        ashParticleEffect.startAshEffect();
        new LimboGenerator();

        getCommand("flx").setExecutor(giveCommand);
        getCommand("flx").setTabCompleter(new CustomGiveTabCompleter());

        startInventoryCheck();
    }

    @Override
    public void onDisable(){
        artifacts.unregisterRecipes();
    }

    public void startInventoryCheck() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    artifacts.checkInventorySlot(player); // Проверяем 17-й слот на предмет амулета
                }
            }
        }.runTaskTimer(this, 0L, 10L); // Запуск сразу и повторение каждые 20 тиков (1 секунда)
    }

}
