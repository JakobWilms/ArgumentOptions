package tk.picraftmc.manhunt.listener;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tk.picraftmc.manhunt.scoreboard.MyScoreboard;
import tk.picraftmc.manhunt.utils.MainUtils;

public class ListenerPlayerJoinEvent implements Listener {
    @EventHandler
    public void onEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.setScoreboard(MyScoreboard.scoreboard);
        if (MainUtils.isInGame(player)) {
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Willkommen zurück!!! Du kannst weiterspielen!");
        } else {
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Du befindest dich nicht in der aktuellen Runde oder es läuft aktuell keine Runde. Bitte warten.");
            player.setGameMode(GameMode.SPECTATOR);
        }
    }
}
