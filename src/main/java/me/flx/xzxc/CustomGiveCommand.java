package me.flx.xzxc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CustomGiveCommand implements CommandExecutor {

    private final Artifacts customItems;

    public CustomGiveCommand(Artifacts customItems) {
        this.customItems = customItems;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 3 || !args[0].equalsIgnoreCase("give") || !args[1].equalsIgnoreCase("custom")) {
            sender.sendMessage("§cНеправильная команда!");
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cТолько игрок может использовать эту команду!");
            return true;
        }

        Player player = (Player) sender;
        String itemName = args[2].toLowerCase(); // Название предмета передается как третий аргумент

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
}

