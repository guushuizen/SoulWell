package me.iamguus.soulwell.inventory;

import me.iamguus.soulwell.utils.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * Created by Guus on 27-12-2015.
 */
public class MainInventory {

    private static MainInventory instance;

    public Inventory getInventory() {
        Inventory inv = Bukkit.createInventory(null, 45, "SoulWell Gambler");

        ItemStack glass = new ItemStack(Material.THIN_GLASS);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(ChatColor.WHITE + "What will you get?");
        glassMeta.setLore(Arrays.asList(ChatColor.GRAY + "Click on me to start the gamble."));
        glass.setItemMeta(glassMeta);

        inv.setItem(13, glass);
        inv.setItem(31, glass);

        for (int i = 18; i < 27; i++) {
            inv.setItem(i, InventoryUtil.get().getRandomReward().getItem());
        }

        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);
            if (item == null) {
                inv.setItem(i, InventoryUtil.get().getGlass());
            }
        }

        return inv;
    }

    public static MainInventory get() {
        if (instance == null) {
            instance = new MainInventory();
        }

        return instance;
    }
}
