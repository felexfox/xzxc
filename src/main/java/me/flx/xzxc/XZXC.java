package me.flx.xzxc;

import me.flx.xzxc.Commands.FlxCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

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

        Bukkit.getPluginManager().registerEvents(new OldWorldListener(this), this);
        AshParticleEffect ashParticleEffect = new AshParticleEffect(this);
        ashParticleEffect.startAshEffect();
        new LimboGenerator();

        FlxCommandExecutor flxCommandExecutor = new FlxCommandExecutor(customItems);
        this.getCommand("flx").setExecutor(flxCommandExecutor);
        this.getCommand("flx").setTabCompleter(flxCommandExecutor);

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
