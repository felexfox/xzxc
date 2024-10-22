package me.flx.xzxc;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OldWorldListener implements Listener {

    MiniMessage mm = MiniMessage.miniMessage();

    // Хранение времени последнего сообщения для каждого условия
    private final Map<Player, Long> lastMessageTime256 = new HashMap<>();
    private final Map<Player, Long> lastMessageTime260 = new HashMap<>();
    private final Map<Player, Long> lastMessageTime265 = new HashMap<>();
    private final Map<Player, Long> mlastEffectTime = new HashMap<>();

    private static final long MESSAGE_DELAY = 15000;
    private static final int EFFECT_DELAY = 1;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        int x = location.getBlockX();
        int z = location.getBlockZ();
        int y = location.getBlockY();
        long currentTime = System.currentTimeMillis(); // Текущее время

        // Проверка для координаты X или Z = 256
        if (x == 256 || z == 256 || x == -256 || z == -256) {
            if (canSendMessage(lastMessageTime256, player, currentTime)) {
                sendPlayerMessage(player, "<color:#94FFD8><bold>[" + player.getName() + "]</color> <color:#1E201E> >> </color>Что-то мне не хорошо.. Мне стоит лучше уйти отсюда...");
            }
            if (canSendMessage(mlastEffectTime, player, currentTime)) {
                player.removePotionEffect(PotionEffectType.POISON);
                player.removePotionEffect(PotionEffectType.DARKNESS);
            }

        }

        // Проверка для координаты X или Z = 260
        if (x >= 260 || z >= 260 || x <= -260 || z <= -260) {
            if (canSendMessage(lastMessageTime260, player, currentTime)) {
                sendPlayerMessage(player, "<color:#94FFD8><bold>[" + player.getName() + "]</color> <color:#1E201E> >> </color>Мне всё хуже и хуже.. Всё-таки нужно вернуться...");
                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, -1, 3, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, -1, 3, false, false));
            }
        }

        // Проверка для координаты X или Z = 265
        if (x >= 265 || z >= 265 || x <= -265 || z <= -265) {
            if (canSendMessage(lastMessageTime265, player, currentTime)) {
                sendPlayerMessage(player, "<red>[" + player.getName() + "<red>]<color:#1E201E> >> </color> Мне осталось совсем мало...");
                player.setHealth(1.0);  // Устанавливаем здоровье на 0.5 сердца
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, -1, 5, false, false));
                player.sendActionBar(mm.deserialize("<color:#3C3D37>Вы были отпрвленны в лимбо.</color>"));
                sendPlayerMessage(player, "<red><bold>[???]</red><color:#1E201E> >> </color>Ты попал в лимбо.. Тебе просто нужно не пытаться выйти за пределы, которые поставленны тем миром, в котором ты был. Не попадай больше сюда, пожалуйста.");
                Optional.ofNullable(Bukkit.getWorld("limbo")).ifPresent(world -> player.teleport(
                        new Location(world, 0, 100, 0)
                ));
                player.removePotionEffect(PotionEffectType.SLOW_FALLING);
                player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 15, 5, false, false));

            }
        }
        if (player.getWorld().getName().equals("limbo")) {
            if (y == 0) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 15, 5, false, false));
                Optional.ofNullable(Bukkit.getWorld("world")).ifPresent(world -> player.teleport(
                        new Location(world, 0, 100, 0)
                ));
            }
        }
    }

    private boolean canSendMessage(Map<Player, Long> lastMessageTimeMap, Player player, long currentTime) {
        Long lastTime = lastMessageTimeMap.get(player);
        if (lastTime == null || currentTime - lastTime >= MESSAGE_DELAY) {
            lastMessageTimeMap.put(player, currentTime); // Обновляем время последнего сообщения
            return true;
        }
        if (lastTime == null || currentTime - lastTime >= EFFECT_DELAY) {
            lastMessageTimeMap.put(player, currentTime); // Обновляем время последнего сообщения
        }
        return false;
    }

    private void sendPlayerMessage(Player player, String message) {
        player.sendMessage(mm.deserialize(message));
    }

}
