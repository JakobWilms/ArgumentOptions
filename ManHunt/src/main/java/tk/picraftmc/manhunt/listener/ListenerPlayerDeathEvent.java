package tk.picraftmc.manhunt.listener;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import tk.picraftmc.manhunt.Globals;

public class ListenerPlayerDeathEvent implements Listener {
    @EventHandler
    public void onEvent(PlayerDeathEvent event) {
        Player player = event.getEntity();

        YamlConfiguration yamlConfiguration = Globals.getYamlConfiguration();
    }
}
