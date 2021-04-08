package tk.picraftmc.manhunt;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Globals {
    private static YamlConfiguration yamlConfiguration;
    private static JavaPlugin javaPlugin;

    public static JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }

    public static void setJavaPlugin(JavaPlugin javaPlugin1) {
        javaPlugin = javaPlugin1;
    }

    public static YamlConfiguration getYamlConfiguration() {
        return yamlConfiguration;
    }

    public static void setYamlConfiguration(YamlConfiguration yamlConfiguration1) {
        yamlConfiguration =yamlConfiguration1;
    }
}
