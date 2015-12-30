package me.iamguus.soulwell.utils;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * Created by Guus on 27-12-2015.
 */
public class InventoryUtil {

    private static InventoryUtil instance;

    public Reward getRandomReward() {
        Set<Reward> allRandomRewards = Reward.getAllRewards(Reward.getRandomType());
        while (allRandomRewards.isEmpty()) {
            allRandomRewards = Reward.getAllRewards(Reward.getRandomType());
        }

        return new ArrayList<Reward>(allRandomRewards).get(new Random().nextInt(allRandomRewards.size()));
    }

    public ItemStack getGlass() {
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta im = item.getItemMeta();
        Random random = new Random();
        item.setDurability((short) 15);
        im.setDisplayName("");
        item.setItemMeta(im);
        return item;
    }

    public static InventoryUtil get() {
        if (instance == null) {
            instance = new InventoryUtil();
        }

        return instance;
    }
}
