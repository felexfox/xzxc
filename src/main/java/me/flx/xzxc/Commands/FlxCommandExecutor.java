package me.flx.xzxc.Commands;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.flx.xzxc.Artifacts;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class FlxCommandExecutor implements CommandExecutor, TabCompleter {
    private final Artifacts customItems;

    public FlxCommandExecutor(Artifacts customItems) {
        this.customItems = customItems;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Эту команду может использовать только игрок.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage("Использование: /flx <cape|give>");
            return true;
        }

        // Обработка команды "cape"
        if (args[0].equalsIgnoreCase("cape")) {
            if (args.length != 2) {
                player.sendMessage("Использование: /flx cape <ссылка_на_плащ>");
                return true;
            }
            String capeUrl = args[1];
            try {
                String textureJson = "{\"textures\":{\"CAPE\":{\"url\":\"" + capeUrl + "\"}}}";
                String encodedTexture = Base64.getEncoder().encodeToString(textureJson.getBytes());

                GameProfile profile = getProfile(player);
                profile.getProperties().removeAll("textures");
                profile.getProperties().put("textures", new Property("textures", encodedTexture));

                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.hidePlayer(player);
                    onlinePlayer.showPlayer(player);
                }

                player.sendMessage("Плащ успешно изменён!");
            } catch (Exception e) {
                player.sendMessage("Не удалось изменить плащ.");
                e.printStackTrace();
            }
            return true;
        }

        // Обработка команды "give custom"
        if (args[0].equalsIgnoreCase("give") && args.length > 2 && args[1].equalsIgnoreCase("custom")) {
            String itemName = args[2].toLowerCase();

            switch (itemName) {
                case "amulet_of_life":
                    player.getInventory().addItem(customItems.createAmuletOfLife());
                    player.sendMessage("§aВы получили Амулет Жизни ур.1!");
                    break;
                case "amulet_of_life2":
                    player.getInventory().addItem(customItems.createAmuletOfLife2());
                    player.sendMessage("§aВы получили Амулет Жизни ур.2!");
                    break;
                case "amulet_of_strenght":
                    player.getInventory().addItem(customItems.createAmuletOfStrenght());
                    player.sendMessage("§aВы получили Амулет Силы ур.1!");
                    break;
                case "amulet_of_strenght2":
                    player.getInventory().addItem(customItems.createAmuletOfStrenght2());
                    player.sendMessage("§aВы получили Амулет Силы ур.2!");
                    break;
                default:
                    player.sendMessage("§cНеизвестный предмет: " + itemName);
                    break;
            }
            return true;
        }

        // Если команда не распознана
        player.sendMessage("Использование: /flx <cape|give>");
        return true;
    }

    private GameProfile getProfile(Player player) throws NoSuchFieldException, IllegalAccessException {
        Field profileField = player.getClass().getDeclaredField("profile");
        profileField.setAccessible(true);
        return (GameProfile) profileField.get(player);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> suggestions = new ArrayList<>();

        if (args.length == 1) {
            suggestions.add("cape");
            suggestions.add("give");
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("cape")) {
                suggestions.add("<cape_url>");
            } else if (args[0].equalsIgnoreCase("give")) {
                suggestions.add("custom");
            }
        } else if (args.length == 3 && args[1].equalsIgnoreCase("custom")) {
            suggestions.add("amulet_of_life");
            suggestions.add("amulet_of_life2");
            suggestions.add("amulet_of_strenght");
            suggestions.add("amulet_of_strenght2");
        }

        return suggestions;
    }
}

