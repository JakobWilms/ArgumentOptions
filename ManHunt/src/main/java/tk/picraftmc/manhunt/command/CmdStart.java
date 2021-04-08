package tk.picraftmc.manhunt.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import tk.picraftmc.manhunt.Globals;
import tk.picraftmc.manhunt.scoreboard.MyScoreboard;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CmdStart implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {

            int random = MyScoreboard.setScoreboard();

            Player[] playersList = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(playersList);
            List<Player> players = Arrays.asList(playersList);

            JavaPlugin plugin = Globals.getJavaPlugin();
            plugin.saveResource("config.yml", true);
            File file = new File(plugin.getDataFolder(), "tk/picraftmc/config.yml");
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

            yamlConfiguration.set("hunted1", "Gleich_Oben");
            int i = 1;
            if (!(random <= 0)) {
                yamlConfiguration.set("hunted2", players.get(random).getDisplayName());
                for (Player online : players) {
                    if (online != Bukkit.getPlayer("Gleich_Oben") && online != players.get(random)) {
                        yamlConfiguration.set("hunter" + i, online.getDisplayName());
                    }
                    i++;
                }

            } else if (random == 0) {
                for (Player online : players) {
                    if (online != Bukkit.getPlayer("Gleich_Oben")) {
                        yamlConfiguration.set("hunter" + i, online.getDisplayName());
                    }
                    i++;
                }
            }

            for (Player online : players) {
                online.sendTitle(ChatColor.GOLD + "" + ChatColor.BOLD + "START!!!", "", 20 , 200, 20);
                online.setGameMode(GameMode.SURVIVAL);
                online.getInventory().clear();
            }

            try {
                yamlConfiguration.save(file);
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error saving " + file.getName() + "!!!");
            }
        }
        return false;
    }
}
