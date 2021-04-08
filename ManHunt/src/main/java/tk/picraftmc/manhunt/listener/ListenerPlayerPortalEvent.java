package tk.picraftmc.manhunt.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import tk.picraftmc.manhunt.utils.MainUtils;


public class ListenerPlayerPortalEvent implements Listener {
    @EventHandler
    public void onEvent(PlayerPortalEvent event) {
        Location locationTo = event.getTo();
        Location locationFrom = event.getFrom();
        Player player = event.getPlayer();

        if (MainUtils.isInGame(player)) {

            String from = "";
            String to = "";
            if (locationTo != null) {
                if (locationFrom.getWorld() == Bukkit.getWorld("world")) {
                    from = "die Oberwelt";
                } else if (locationFrom.getWorld() == Bukkit.getWorld("world_nether")) {
                    from = "den Nether";
                } else if (locationFrom.getWorld() == Bukkit.getWorld("world_the_end")) {
                    from = "das Ende";
                }
                if (locationTo.getWorld() == Bukkit.getWorld("world")) {
                    to = "die Oberwelt";
                } else if (locationTo.getWorld() == Bukkit.getWorld("world_nether")) {
                    to = "den Nether";
                } else if (locationTo.getWorld() == Bukkit.getWorld("world_the_end")) {
                    to = "das Ende";
                }
                Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.ITALIC + player.getDisplayName() + " verlässt " + from + " und betrittt " + to + "!");
            }
        }
    }
}
