package tk.picraftmc.manhunt;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import tk.picraftmc.manhunt.command.CmdReset;
import tk.picraftmc.manhunt.command.CmdStart;
import tk.picraftmc.manhunt.listener.ListenerEntityDeathEvent;
import tk.picraftmc.manhunt.listener.ListenerPlayerDeathEvent;
import tk.picraftmc.manhunt.listener.ListenerPlayerJoinEvent;
import tk.picraftmc.manhunt.listener.ListenerPlayerPortalEvent;

import java.io.File;
import java.util.Objects;

public final class ManHunt extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new ListenerPlayerJoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerEntityDeathEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerPlayerDeathEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerPlayerPortalEvent(), this);

        Objects.requireNonNull(this.getCommand("start")).setExecutor(new CmdStart());
        Objects.requireNonNull(this.getCommand("reset")).setExecutor(new CmdReset());

        Globals.setJavaPlugin(this);

        this.saveResource("config.yml", false);
        File file = new File(this.getDataFolder(), "tk/picraftmc/config.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        Globals.setYamlConfiguration(yamlConfiguration);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
