package me.iamguus.soulwell.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

/**
 * Created by Guus on 26-12-2015.
 */
public class ConfigUtil {

    private static ConfigUtil instance;

    private File locationFile;
    private FileConfiguration locationConf;

    private File rewardsFile;
    private FileConfiguration rewardsConf;

    private File configFile;
    private FileConfiguration configConf;

    public void setup(Plugin p) {
        locationFile = new File(p.getDataFolder(), "locations.yml");
        if (!locationFile.exists()) {
            try {
                locationFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        locationConf = YamlConfiguration.loadConfiguration(locationFile);

        rewardsFile = new File(p.getDataFolder(), "rewards.yml");
        if (!rewardsFile.exists()) {
            p.saveResource("rewards.yml", true);
        }
        rewardsConf = YamlConfiguration.loadConfiguration(rewardsFile);

        configFile = new File(p.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            p.saveResource("config.yml", true);
        }

        configConf = YamlConfiguration.loadConfiguration(configFile);
    }

    public File getLocationFile() { return this.locationFile; }

    public FileConfiguration getLocation() { return this.locationConf; }

    public void saveLocationConfig() {
        try {
            this.locationConf.save(this.locationFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public File getRewardsFile() { return this.rewardsFile; }

    public FileConfiguration getRewards() { return this.rewardsConf; }

    public void saveRewardsConfig() {
        try {
            this.rewardsConf.save(this.rewardsFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public File getConfigFile() { return this.configFile; }

    public FileConfiguration getConfig() { return this.configConf; }

    public void saveConfig() {
        try {
            this.configConf.save(this.configFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ConfigUtil get() {
        if (instance == null) {
            instance = new ConfigUtil();
        }

        return instance;
    }
}
