package tk.picraftmc.manhunt.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import tk.picraftmc.manhunt.Globals;

import java.io.File;
import java.util.Objects;

public class MainUtils {
    public static boolean isInGame(Player player) {
        JavaPlugin javaPlugin = Globals.getJavaPlugin();

        File file = new File(javaPlugin.getDataFolder(), "tk/picraftmc/config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        String dp = player.getDisplayName();
        if (config.getString("hunted1") != null &&
                config.getString("hunted2") != null &&
                config.getString("hunter1") != null &&
                config.getString("hunter2") != null &&
                config.getString("hunter3") != null &&
                config.getString("hunter4") != null &&
                config.getString("hunter5") != null &&
                config.getString("hunter6") != null &&
                config.getString("hunter7") != null &&
                config.getString("hunter8") != null &&
                config.getString("hunter9") != null) {

            return (Objects.requireNonNull(config.getString("hunted1")).equals(dp) ||
                    Objects.requireNonNull(config.getString("hunted2")).equals(dp) ||
                    Objects.requireNonNull(config.getString("hunter1")).equals(dp) ||
                    Objects.requireNonNull(config.getString("hunter2")).equals(dp) ||
                    Objects.requireNonNull(config.getString("hunter3")).equals(dp) ||
                    Objects.requireNonNull(config.getString("hunter4")).equals(dp) ||
                    Objects.requireNonNull(config.getString("hunter5")).equals(dp) ||
                    Objects.requireNonNull(config.getString("hunter6")).equals(dp) ||
                    Objects.requireNonNull(config.getString("hunter7")).equals(dp) ||
                    Objects.requireNonNull(config.getString("hunter8")).equals(dp) ||
                    Objects.requireNonNull(config.getString("hunter9")).equals(dp));
        } else {
            return false;
        }
    }
}
