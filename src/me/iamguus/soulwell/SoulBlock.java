package me.iamguus.soulwell;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import me.iamguus.soulwell.utils.ConfigUtil;
import me.iamguus.soulwell.utils.LocationUtil;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Created by Guus on 26-12-2015.
 */
public class SoulBlock {

    //ID = int

    private int id;
    private Block loc;

    public SoulBlock(int id, Block loc) {
        this.id = id;
        this.loc = loc;
    }

    public int getID() { return this.id; }

    public Block getLocation() { return this.loc; }

    public void setLocation(Block loc) { this.loc = loc; }

    public void save() {
        FileConfiguration config = ConfigUtil.get().getLocation();
        config.set("soulwells." + id + ".id", id);
        config.set("soulwells." + id + ".loc", LocationUtil.get().serialize(loc.getLocation()));
        ConfigUtil.get().saveLocationConfig();
    }

    public void destroy() {
        FileConfiguration config = ConfigUtil.get().getLocation();
        config.set("soulwells." + this.id, null);
        ConfigUtil.get().saveLocationConfig();
    }

    public static SoulBlock loadFromConfig(ConfigurationSection section) {
        int id = section.getInt("id");
        Block block = LocationUtil.get().deserialize(section.getString("loc")).getBlock();

        return new SoulBlock(id, block);
    }
}
