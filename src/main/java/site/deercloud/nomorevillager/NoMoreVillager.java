package site.deercloud.nomorevillager;

import org.bukkit.plugin.java.JavaPlugin;

public final class NoMoreVillager extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        configManager = new ConfigManager();

        getServer().getPluginManager().registerEvents(new Events(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static NoMoreVillager instance;
    public static ConfigManager configManager;
}
