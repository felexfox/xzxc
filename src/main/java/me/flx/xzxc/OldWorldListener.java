package me.flx.xzxc;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class OldWorldListener implements Listener {

    MiniMessage mm = MiniMessage.miniMessage();

    // Хранение времени последнего сообщения для каждого условия
    private final Map<Player, Long> lastMessageTime256 = new HashMap<>();
    private final Map<Player, Long> lastMessageTime260 = new HashMap<>();
    private final Map<Player, Long> lastMessageTime265 = new HashMap<>();
    private final Map<Player, Long> lastMessageTimeMinus256 = new HashMap<>();
    private final Map<Player, Long> lastMessageTimeMinus260 = new HashMap<>();
    private final Map<Player, Long> lastMessageTimeMinus265 = new HashMap<>();

    private static final long MESSAGE_DELAY = 5 * 60 * 1000; // 5 минут в миллисекундах

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        int x = location.getBlockX();
        int z = location.getBlockZ();
        long currentTime = System.currentTimeMillis(); // Текущее время

        // Проверка для координаты X или Z = 256
        if (x == 256 || z == 256) {
            if (canSendMessage(lastMessageTime256, player, currentTime)) {
                sendPlayerMessage(player, "<color:#94FFD8>[</color>" + player.getName() + "<color:#94FFD8>]</color> <color:#1E201E> >> </color>Что-то мне не хорошо.. Мне стоит лучше уйти отсюда...");
                player.getActivePotionEffects().clear(); // Очищаем все эффекты
            }
        }

        // Проверка для координаты X или Z = 260
        if (x == 260 || z == 260) {
            if (canSendMessage(lastMessageTime260, player, currentTime)) {
                sendPlayerMessage(player, "<color:#94FFD8>[</color>" + player.getName() + "<color:#94FFD8>]</color> <color:#1E201E> >> </color>Мне всё хуже и хуже.. Всё-таки нужно вернуться...");
                // Даем игроку эффект тошноты уровня 1 навсегда (до снятия)
                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 999999, 1, false, false));
            }
        }

        // Проверка для координаты X или Z = 265
        if (x == 265 || z == 265) {
            if (canSendMessage(lastMessageTime265, player, currentTime)) {
                sendPlayerMessage(player, "<red>[<r>" + player.getName() + "<red>]<r><color:#1E201E> >> </color> Мне осталось совсем мало...");
                player.setHealth(1.0);  // Устанавливаем здоровье на 0.5 сердца

                // Выполняем команду для телепортации игрока в "Лимбо"
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute as " + player.getName() + " in limbo run tp " + player.getName() + " 0 64 0");
                player.sendActionBar(mm.deserialize("<color:#3C3D37>Вы были отпрвленны в лимбо.</color>"));
            }
        }

        // Проверка для отрицательных координат
        if (x == -256 || z == -256) {
            if (canSendMessage(lastMessageTimeMinus256, player, currentTime)) {
                sendPlayerMessage(player, "<color:#94FFD8><bold>[</color>" + player.getName() + "<color:#94FFD8><bold>]</color> <color:#1E201E> >> </color>Что-то мне не хорошо.. Мне стоит лучше уйти отсюда...");
                player.getActivePotionEffects().clear();
            }
        }

        if (x == -260 || z == -260) {
            if (canSendMessage(lastMessageTimeMinus260, player, currentTime)) {
                sendPlayerMessage(player, "<color:#94FFD8><bold>[</color>" + player.getName() + "<color:#94FFD8><bold>]</color> <color:#1E201E> >> </color>Мне всё хуже и хуже.. Всё-таки нужно вернуться...");
                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 999999, 1, false, false));
            }
        }

        if (x == -265 || z == -265) {
            if (canSendMessage(lastMessageTimeMinus265, player, currentTime)) {
                sendPlayerMessage(player, "<bold><red>[" + player.getName() + "]</bold><color:#1E201E> >> </color><white> Мне осталось совсем мало...");
                player.setHealth(1.0);  // Устанавливаем здоровье на 0.5 сердца

                // Выполняем команду для телепортации игрока в "Лимбо"
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute as " + player.getName() + " in limbo run tp " + player.getName() + " 0 64 0");
                player.sendActionBar(mm.deserialize("<color:#3C3D37>Вы были отпрвленны в лимбо.</color>"));
            }
        }
    }

    private boolean canSendMessage(Map<Player, Long> lastMessageTimeMap, Player player, long currentTime) {
        Long lastTime = lastMessageTimeMap.get(player);
        if (lastTime == null || currentTime - lastTime >= MESSAGE_DELAY) {
            lastMessageTimeMap.put(player, currentTime); // Обновляем время последнего сообщения
            return true;
        }
        return false; // Сообщение нельзя отправить
    }

    private void sendPlayerMessage(Player player, String message) {
        player.sendMessage(mm.deserialize(message));
    }
}
