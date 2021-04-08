package tk.picraftmc.manhunt.listener;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import tk.picraftmc.manhunt.utils.MainUtils;

public class ListenerEntityDeathEvent implements Listener {
    @EventHandler
    public void onEvent(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.ENDER_DRAGON) {
            EnderDragon dragon = (EnderDragon) entity;
            Player player = dragon.getKiller();

            if (player != null) {
                if (MainUtils.isInGame(player)) {
                    Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "ENDE!!!");
                    Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + player.getDisplayName() + " hat den Enderdrachen besiegt!!!");
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        String message = ChatColor.GOLD + "" + ChatColor.BOLD + online.getDisplayName() + " hat den Enderdrachen besiegt!";
                        online.sendTitle(ChatColor.GOLD + "ENDE!!!", message, 20, 400, 20);
                        online.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                        online.setGameMode(GameMode.SPECTATOR);
                    }
                }
            }
        }
    }
}
