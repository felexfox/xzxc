package me.flx.xzxc;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class OldWorldListener implements Listener {

    BukkitScheduler scheduler = Bukkit.getScheduler();

    private final XZXC plugin;

    public OldWorldListener(XZXC plugin) {
        this.plugin = plugin;
    }

    MiniMessage mm = MiniMessage.miniMessage();

    // Хранение времени последнего сообщения для каждого условия
    private final Map<Player, Long> lastMessageTime256 = new HashMap<>();
    private final Map<Player, Long> lastMessageTime260 = new HashMap<>();
    private final Map<Player, Long> lastMessageTime265 = new HashMap<>();

    private static final long MESSAGE_DELAY = 2000;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent join){
        Player player = join.getPlayer();
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(12);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent move) {
        Player player = move.getPlayer();
        Location location = player.getLocation();
        int x = location.getBlockX();
        int z = location.getBlockZ();
        int y = location.getBlockY();
        long currentTime = System.currentTimeMillis(); // Текущее время

        if (player.getWorld().getName().equals("world")) {
            if (x == 0 && z == 0 && y == 99){
                player.playSound(player.getLocation(), "custom.player.teleport", 0.3F, 1.0F);
                player.removePotionEffect(PotionEffectType.BLINDNESS);
            }
            if (x == 256 || z == 256 || x == -256 || z == -256) {
                player.setViewDistance(8);
                if (canSendMessage(lastMessageTime256, player, currentTime)) {
                    sendPlayerMessage(player, "<color:#94FFD8><bold>[" + player.getName() + "]</color> <color:#1E201E> >> </color>Что-то мне не хорошо.. Мне стоит лучше уйти отсюда...");
                    player.removePotionEffect(PotionEffectType.POISON);
                    player.removePotionEffect(PotionEffectType.DARKNESS);
                }
            }
            if (x == 260 || z == 260 || x == -260 || z == -260) {
                player.setViewDistance(2);
                if (canSendMessage(lastMessageTime260, player, currentTime)) {
                    sendPlayerMessage(player, "<color:#94FFD8><bold>[" + player.getName() + "]</color> <color:#1E201E> >> </color>Мне всё хуже и хуже.. Всё-таки нужно вернуться...");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, -1, 3, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, -1, 3, false, false));
                }
            }
            if (x == 263 || z == 263 || x == -263 || z == -263) {
                if (canSendMessage(lastMessageTime265, player, currentTime)) {
                    sendPlayerMessage(player, "<red><bold>[" + player.getName() + "]</red><color:#1E201E> >> </color> Мне осталось совсем мало...");
                    player.playSound(player.getLocation(), "custom.player.heartbeat", 1.0F, 1.0F);
                    player.setHealth(1.0);  // Устанавливаем здоровье на 0.5 сердца
                    scheduler.runTaskLater(plugin, () -> {
                        player.sendTitle("ƀ", "", 10, 20, 10);
                    }, 20);
                }
            }
            if (x >= 265 || z >= 265 || x <= -265 || z <= -265) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, -1, 5, false, false));
                player.sendActionBar(mm.deserialize("<color:#3C3D37>Вы были отпрвленны в лимбо.</color>"));
                player.removePotionEffect(PotionEffectType.POISON);

                scheduler.runTaskLater(plugin, () -> {
                    player.playSound(player.getLocation(), "custom.player.teleport", 0.2F, 1.3F);
                    Optional.ofNullable(Bukkit.getWorld("limbo")).ifPresent(world -> player.teleport(
                            new Location(world, 0.5, 75, 0.5)
                    ));
                }, 30);
            }
        }
        if (player.getWorld().getName().equals("limbo")) {
            if (y == 70) {
                if (canSendMessage(lastMessageTime265, player, currentTime)) {
                    sendPlayerMessage(player, "<red><bold>[???]</red><color:#1E201E> >> </color>Ты попал в лимбо.. Тебе просто нужно не пытаться выйти за пределы, которые поставленны тем миром, в котором ты был. Не попадай больше сюда, пожалуйста.");
                }
                player.removePotionEffect(PotionEffectType.DARKNESS);
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, -1, 5, false, false));
                player.removePotionEffect(PotionEffectType.SLOW_FALLING);
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 80, 5, false, false));
            }
            if (x == 1 || z == 1 || x == -1 || z == -1 && y == 50) {
                scheduler.runTaskLater(plugin, () -> {
                    player.playSound(player.getLocation(), "custom.player.teleport", 0.2F, 1.3F);
                    player.sendTitle("ƀ", "", 20, 60, 20);
                }, 20);
            }
            if (x == 3 || z == 3 || x == -3 || z == -3 && y == 50) {
                player.setViewDistance(8);
                scheduler.runTaskLater(plugin, () ->{
                    for (Player player1 : Bukkit.getOnlinePlayers()) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 3, false, false));
                        Optional.ofNullable(Bukkit.getWorld("world")).ifPresent(world -> player.teleport(
                                new Location(world, 0, 100, 0)
                        ));
                        if (has50percent()) {
                            PotionEffect effect = new PotionEffect(PotionEffectType.SLOW_FALLING, 300, 5, false, false);
                            player1.addPotionEffect(effect);
                            player1.sendActionBar("luck");
                        }else{
                            player1.sendActionBar("unluck");
                        }
                    }
                }, 20);
            }
        }
    }

    private boolean canSendMessage(Map<Player, Long> lastMessageTimeMap, Player player, long currentTime) {
        Long lastTime = lastMessageTimeMap.get(player);
        if (lastTime == null || currentTime - lastTime >= MESSAGE_DELAY) {
            lastMessageTimeMap.put(player, currentTime); // Обновляем время последнего сообщения
            return true;
        }
        return false;
    }

    private void sendPlayerMessage(Player player, String message) {
        player.sendMessage(mm.deserialize(message));
    }

    private boolean has50percent(){
        Random random = new Random();
        int chance = random.nextInt(100);
        return chance < 80;
    }

}
