package me.iamguus.soulwell.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Guus on 28-12-2015.
 */
public class Reward {

    private String name;
    private RewardType type;
    private ItemStack item;
    private ItemStack itemReward;
    private String cmdReward;

    Reward(String name, RewardType type, ItemStack item, ItemStack itemReward, String cmdReward) {
        this.name = name;
        this.type = type;
        this.item = item;
        this.itemReward = itemReward;
        this.cmdReward = cmdReward;
    }

    public String getName() { return this.name; }

    public RewardType getType() { return this.type; }

    public ItemStack getItem() { return this.item; }

    public ItemStack getItemReward() { return this.itemReward; }

    public String getCommandReward() { return this.cmdReward; }

    private static Set<Reward> allRewards = new HashSet<Reward>();

    public static void loadAllRewards() {
        for (String s : ConfigUtil.get().getRewards().getConfigurationSection("rewards").getKeys(false)) {
            if (loadReward(ConfigUtil.get().getRewards().getConfigurationSection("rewards." + s)) != null) {
                allRewards.add(loadReward(ConfigUtil.get().getRewards().getConfigurationSection("rewards." + s)));
            }
        }
    }

    private static Reward loadReward(ConfigurationSection section) {
        String name = section.getString("name");
        RewardType type = RewardType.valueOf(section.getString("type").toUpperCase());
        ItemStack item = ItemStackUtil.get().deserialize(section.getString("item"));
        ItemStack itemReward = null;
        String cmdReward = "";
        if (section.contains("item_reward")) {
            itemReward = ItemStackUtil.get().deserialize(section.getString("item_reward"));
        }
        if (section.contains("cmd_reward")) {
            cmdReward = section.getString("cmd_reward");
        }

        if (itemReward == null && cmdReward.equals("")) {
            System.out.println("Reward '" + name + "' does not have any rewards (no command/item). This reward will not be loaded until it has a reward.");
            return null;
        }

        return new Reward(name, type, item, itemReward, cmdReward);
    }

    public static Set<Reward> getAllRewards() {
        return allRewards;
    }

    public static Set<Reward> getAllRewards(RewardType type) {
        Set<Reward> allRewards = new HashSet<Reward>();
        for (Reward loop : allRewards) {
            if (loop.getType() == type) {
                allRewards.add(loop);
            }
        }
        return allRewards;
    }

    public static RewardType getRandomType() {
        double d = Math.random() * 100;
        for (RewardType loop : RewardType.values()) {
            if (d <= (100 - loop.getChance())) {
                return loop;
            }
        }
        return RewardType.COMMON;
    }
}

enum RewardType {

    COMMON("Common", 40, ChatColor.GRAY),
    UNCOMMON("Uncommon", 25, ChatColor.GREEN),
    RARE("Rare", 20, ChatColor.BLUE),
    EPIC("Epic", 10, ChatColor.RED),
    LEGENDARY("Legendary", 5, ChatColor.DARK_PURPLE);

    private String name;
    private int chance;
    private ChatColor color;

    private RewardType(String name, int chance, ChatColor color) {
        this.name = name;
        this.chance = chance;
        this.color = color;
    }

    public String getName() { return this.name; }

    public int getChance() { return this.chance; }

    public ChatColor getColor() { return this.color; }
}
