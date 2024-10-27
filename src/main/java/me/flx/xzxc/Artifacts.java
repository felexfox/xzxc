package me.flx.xzxc;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Artifacts {

    private final XZXC plugin;

    public Artifacts(XZXC plugin) {
        this.plugin = plugin;
    }

    MiniMessage mm = MiniMessage.miniMessage();

    public ItemStack createAmuletOfLife() {
        ItemStack item = new ItemStack(Material.POPPED_CHORUS_FRUIT);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setCustomModelData(1001);
            meta.displayName(mm.deserialize("<italic:false><yellow>Амулет Жизни ур.1"));
            String loreText1 = "<italic:false><grey>Этот амулет дарует вам дополнительные ";
            String loreText2 = "<italic:false><grey>жизни когда одет в специальный слот.";
            String loreText3 = "<italic:false><blue>Modoru EX: Old.";
            meta.lore(List.of(mm.deserialize(loreText1), mm.deserialize(loreText3), mm.deserialize(loreText3)));
            item.setItemMeta(meta);
        }
        return item;
    }

    public ItemStack createAmuletOfLife2() {
        ItemStack item = new ItemStack(Material.POPPED_CHORUS_FRUIT);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setCustomModelData(1002);
            meta.displayName(mm.deserialize("<italic:false><yellow>Амулет Жизни ур.2"));
            String loreText1 = "<italic:false><grey>Этот амулет дарует вам дополнительные";
            String loreText2= "<italic:false><grey>жизни когда одет в специальный слот.";
            String loreText3 = "<italic:false><blue>Modoru EX: Old.";
            meta.lore(List.of(mm.deserialize(loreText1), mm.deserialize(loreText2), mm.deserialize(loreText3)));
            item.setItemMeta(meta);
        }
        return item;
    }

    public ItemStack createAmuletOfStrenght() {
        ItemStack item = new ItemStack(Material.POPPED_CHORUS_FRUIT);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setCustomModelData(1003);
            meta.displayName(mm.deserialize("<italic:false><yellow>Амулет Силы ур.1"));
            String loreText1 = "<italic:false><grey>Этот амулет дарует вам увеличенный";
            String loreText2 = "<italic:false><grey>урон когда одет в специальный слот.";
            String loreText3 = "<italic:false><blue>Modoru EX: Old.";
            meta.lore(List.of(mm.deserialize(loreText1),mm.deserialize(loreText2), mm.deserialize(loreText3)));
            item.setItemMeta(meta);
        }
        return item;
    }

    public ItemStack createAmuletOfStrenght2() {
        ItemStack item = new ItemStack(Material.POPPED_CHORUS_FRUIT);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setCustomModelData(1004);
            meta.displayName(mm.deserialize("<italic:false><yellow>Амулет Силы ур.2"));
            String loreText1 = "<italic:false><grey>Этот амулет дарует вам увеличенный";
            String loreText2 = "<italic:false><grey>урон когда одет в специальный слот.";
            String loreText3 = "<italic:false><blue>Modoru EX: Old.";
            meta.lore(List.of(mm.deserialize(loreText1),mm.deserialize(loreText2), mm.deserialize(loreText3)));
            item.setItemMeta(meta);
        }
        return item;
    }
    public ItemStack createAmuletFragment() {
        ItemStack item = new ItemStack(Material.POPPED_CHORUS_FRUIT); // или выбери другой материал для фрагмента
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setCustomModelData(1005); // Уникальный ID для предмета, если нужен

            // Название предмета
            meta.displayName(MiniMessage.miniMessage().deserialize("<italic:false><yellow>Фрагмент амулета ур.1"));

            // Лор предмета
            String loreText1 = "<italic:false><grey>Часть древних амулетов. Используется для создания";
            String loreText2 = "<italic:false><grey>старинных амулетов.";
            String loreText3 = "<italic:false><blue>Modoru EX: Old.";
            meta.lore(List.of(mm.deserialize(loreText1),mm.deserialize(loreText2) ,mm.deserialize(loreText3)));

            item.setItemMeta(meta);
        }

        return item;
    }

    public ItemStack createAmuletOfSpeed() {
        ItemStack item = new ItemStack(Material.POPPED_CHORUS_FRUIT);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setCustomModelData(1006);

            meta.displayName(MiniMessage.miniMessage().deserialize("<italic:false><yellow>Амулет Скорости ур.1"));

            String loreText1 = "<italic:false><grey>Амулет дарующий вам увеличенную скорость";
            String loreText2 = "<italic:false><grey>когда одет в специальный слот.";
            String loreText3 = "<italic:false><blue>Modoru EX: Old.";
            meta.lore(List.of(mm.deserialize(loreText1),mm.deserialize(loreText2) ,mm.deserialize(loreText3)));

            item.setItemMeta(meta);
        }
        return item;
    }

    public ItemStack createAmuletOfSpeed2() {
        ItemStack item = new ItemStack(Material.POPPED_CHORUS_FRUIT);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setCustomModelData(1007);

            meta.displayName(MiniMessage.miniMessage().deserialize("<italic:false><yellow>Амулет Скорости ур.2"));

            String loreText1 = "<italic:false><grey>Амулет дарующий вам увеличенную скорость";
            String loreText2 = "<italic:false><grey>когда одет в специальный слот.";
            String loreText3 = "<italic:false><blue>Modoru EX: Old.";
            meta.lore(List.of(mm.deserialize(loreText1),mm.deserialize(loreText2) ,mm.deserialize(loreText3)));
        }

        return item;
    }

    // Проверка 17-го слота и выдача эффекта
    public void checkInventorySlot(Player player) {
        ItemStack itemInSlot = player.getInventory().getItem(17); // 17-й слот
        if (itemInSlot != null && itemInSlot.hasItemMeta()) {
            ItemMeta meta = itemInSlot.getItemMeta();
            if (meta.hasCustomModelData()) {
                if (meta.getCustomModelData() == 1001) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 40, 1, false, false)); // Эффект регенерации
                } else if (meta.getCustomModelData() == 1002) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 40, 2, false, false)); // Более сильный эффект
                } else if (meta.getCustomModelData() == 1003) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 40, 0, false, false));
                } else if (meta.getCustomModelData() == 1004) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 40, 1, false, false));
                } else if (meta.getCustomModelData() == 1006) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 0, false, false));
                } else if (meta.getCustomModelData() == 1007) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 1, false, false));
                }
            }
        }
    }

    public void RegisterRecipes() {
        ItemStack amuletFragment = createAmuletFragment();
        ShapedRecipe fragmentRecipe = new ShapedRecipe(new NamespacedKey(plugin, "amulet_fragment"), amuletFragment);
        fragmentRecipe.shape(" gn", "g g", " g ");
        fragmentRecipe.setIngredient('g', Material.GOLD_INGOT);
        fragmentRecipe.setIngredient('n', Material.GOLD_NUGGET);

        // Регистрируем рецепт
        Bukkit.addRecipe(fragmentRecipe);
    }

    public void unregisterRecipes() {
        Bukkit.removeRecipe(new NamespacedKey(plugin, "amulet_fragment"));
    }
}

