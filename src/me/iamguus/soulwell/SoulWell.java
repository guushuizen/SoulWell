package me.iamguus.soulwell;

import me.iamguus.soulwell.commands.SoulwellCommands;
import me.iamguus.soulwell.listeners.ClickListener;
import me.iamguus.soulwell.utils.ConfigUtil;
import me.iamguus.soulwell.utils.Reward;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Guus on 26-12-2015.
 */
public class SoulWell extends JavaPlugin {

    private static Plugin p;

    public void onEnable() {
        this.p = this;

        ConfigUtil.get().setup(this);

        getServer().getPluginManager().registerEvents(new ClickListener(), this);

        Reward.loadAllRewards();

        getCommand("soulwell").setExecutor(new SoulwellCommands());
    }

    public void onDisable() {

    }

    public static Plugin getP() {
        return p;
    }
}
