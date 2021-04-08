package tk.picraftmc.manhunt.command;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;

public class CmdReset implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.setGameMode(GameMode.SPECTATOR);
            online.getInventory().clear();
            online.setHealth(20.0);
            online.setFoodLevel(10);
            online.setSaturation(3.0f);
            online.teleport(Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation());
            online.kickPlayer(ChatColor.GREEN + "Die Welt wird zurückgesetzt!!! \n" + "Bitte warten!!!");
        }
        resetWorld();
        return false;
    }

    private void resetWorld() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "RESETTING");
        File worldFile = new File("world");
        File netherFile = new File("world_nether");
        File endFile = new File("world_the_end");

        boolean b = worldFile.delete();
        boolean b1 = netherFile.delete();
        boolean b2 = endFile.delete();

        if (b && b1 && b2) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "!!!");

            WorldCreator worldCreator = WorldCreator.name("world");
            WorldCreator netherCreator = WorldCreator.name("world_nether").environment(World.Environment.NETHER);
            WorldCreator endCreator = WorldCreator.name("world_the_end").environment(World.Environment.THE_END);

            Bukkit.getWorlds().remove(Bukkit.getWorld("world"));
            Bukkit.getWorlds().remove(Bukkit.getWorld("world_nether"));
            Bukkit.getWorlds().remove(Bukkit.getWorld("world_the_end"));

            Bukkit.createWorld(worldCreator);
            Bukkit.createWorld(netherCreator);
            Bukkit.createWorld(endCreator);

            Bukkit.getWorlds().add(Bukkit.getWorld("world"));
            Bukkit.getWorlds().add(Bukkit.getWorld("world_nether"));
            Bukkit.getWorlds().add(Bukkit.getWorld("world_the_end"));
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Files not deleted!!!");
        }
    }
}
