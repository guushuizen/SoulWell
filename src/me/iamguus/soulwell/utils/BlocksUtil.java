package me.iamguus.soulwell.utils;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.iamguus.soulwell.SoulBlock;
import me.iamguus.soulwell.SoulWell;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.Configuration;

/**
 * Created by Guus on 26-12-2015.
 */
public class BlocksUtil {

    private static BlocksUtil instance;

    public int getNewID() {
        ConfigUtil config = ConfigUtil.get();
        int count = 0;
        if (config.getLocation().getConfigurationSection("soulwells") != null) {
            for (String s : config.getLocation().getConfigurationSection("soulwells").getKeys(false)) {
                count++;
            }
        }
        return count;
    }

    public Hologram createHologram(Block block) {
        Location toPlace = block.getLocation().add(0.5, 2, 0.5);
        Hologram hologram = HologramsAPI.createHologram(SoulWell.getP(), toPlace);

        for (String s : ConfigUtil.get().getConfig().getStringList("lines")) {
            hologram.appendTextLine(ChatColor.translateAlternateColorCodes('&', s));
        }

        return hologram;
    }

    public SoulBlock getByLocation(Location loc) {
        for (String s : ConfigUtil.get().getLocation().getConfigurationSection("soulwells").getKeys(false)) {
            String locString = ConfigUtil.get().getLocation().getString("soulwells." + s + ".loc");
            if (locString.equalsIgnoreCase(LocationUtil.get().serialize(loc))) {
                return SoulBlock.loadFromConfig(ConfigUtil.get().getLocation().getConfigurationSection("soulwells." + s));
            }
        }
        return null;
    }

    public void remove(Location loc) {
        SoulBlock block = getByLocation(loc);
        Hologram hologram = null;

        for (Hologram holoLoop : HologramsAPI.getHolograms(SoulWell.getP())) {
            if (LocationUtil.get().serialize(holoLoop.getLocation()).equalsIgnoreCase(LocationUtil.get().serialize(loc.add(0.5, 2, 0.5)))) {
                hologram = holoLoop;
            }
        }
        block.destroy();

        hologram.delete();
    }

    public static BlocksUtil get() {
        if (instance == null) {
            instance = new BlocksUtil();
        }

        return instance;
    }
}
