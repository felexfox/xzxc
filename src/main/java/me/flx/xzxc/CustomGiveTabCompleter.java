package me.flx.xzxc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CustomGiveTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("give")) {
            return Arrays.asList("give");
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("give")) {
            return Arrays.asList("custom");
        }

        if (args.length == 3 && args[1].equalsIgnoreCase("custom")) {
            return Arrays.asList("amulet_of_life", "amulet_of_life2", "amulet_of_strenght", "amulet_of_strenght2");
        }

        return Collections.emptyList();
    }

}
