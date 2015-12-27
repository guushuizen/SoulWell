package me.iamguus.soulwell;

import me.iamguus.soulwell.commands.SoulwellCommands;
import me.iamguus.soulwell.utils.ConfigUtil;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Guus on 26-12-2015.
 */
public class SoulWell extends JavaPlugin {

    private static Plugin p;

    public void onEnable() {
        this.p = this;

        getCommand("soulwell").setExecutor(new SoulwellCommands());

        ConfigUtil.get().setup(this);
    }

    public void onDisable() {

    }

    public static Plugin getP() {
        return p;
    }
}
