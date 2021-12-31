package tech.clearistic.mckillzone;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MCKillZone extends JavaPlugin {
    @Override
    public void onDisable() {
        super.onDisable();

        Bukkit.getLogger().info(ChatColor.GREEN + "Disabled " + this.getName());
    }

    @Override
    public void onEnable() {
        super.onEnable();

        ConfigurationSerialization.registerClass(LocationCorner.class, "Corner");
        ConfigurationSerialization.registerClass(LocationCube.class, "Cube");

        FileConfiguration config = this.configure();
        boolean isEnabled = config.getBoolean("enabled", false);
        boolean killSpectators = config.getBoolean("killSpectators", false);

        if (!isEnabled) {
            Bukkit.getLogger().info(this.getName() + " disabled by configuration");
            return;
        }

        List<LocationCube> zones = (List<LocationCube>) config.getList("zones");
        Bukkit.getLogger().info("Number of kill zones: " + zones.size());

        zones.forEach(z -> {
            Bukkit.getLogger().info("Processing zone '" + z.getName()
                    + "' in world '" + z.getWorld() + "'");

            getServer().getPluginManager().registerEvents(new MovementListener(z, killSpectators), this);
            Bukkit.getLogger().info("Zone successfully configured.");
        });

        Bukkit.getLogger().info(ChatColor.GREEN + "Enabled " + this.getName());
    }

    private FileConfiguration configure() {
        // only saves if the file doesn't already exist
        this.saveDefaultConfig();

        return this.getConfig();
    }
}
