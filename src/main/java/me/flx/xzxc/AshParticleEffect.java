package me.flx.xzxc;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class AshParticleEffect {

    private final JavaPlugin plugin;

    public AshParticleEffect(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    // Запуск эффекта частиц пепла для игроков в мире "limbo"
    public void startAshEffect() {
        new BukkitRunnable() {
            @Override
            public void run() {
                // Проходим по всем игрокам в мире "limbo"
                for (Player player : Bukkit.getOnlinePlayers()) {
                    World world = player.getWorld();
                    if (world.getName().equals("limbo")) {
                        spawnAshParticles(player);
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 20L); // Запуск таска каждую секунду (20 тиков)
    }

    // Метод для создания частиц вокруг игрока
    private void spawnAshParticles(Player player) {
        Location playerLocation = player.getLocation();
        World world = player.getWorld();
        Random random = new Random();

        // Генерация 20 частиц вокруг игрока в радиусе 10 блоков
        for (int i = 0; i < 20; i++) {
            double offsetX = (random.nextDouble() - 0.5) * 10; // Случайное смещение по X
            double offsetY = random.nextDouble() * 5; // Случайное смещение по Y
            double offsetZ = (random.nextDouble() - 0.5) * 10; // Случайное смещение по Z

            Location particleLocation = playerLocation.clone().add(offsetX, offsetY, offsetZ);
            world.spawnParticle(Particle.ASH, particleLocation, 1, 0, 0, 0, 0);
        }
    }
}
