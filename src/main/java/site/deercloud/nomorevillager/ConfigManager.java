package site.deercloud.nomorevillager;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    public ConfigManager() {
        reload();
    }

    public void reload() {
        NoMoreVillager.instance.saveDefaultConfig();
        NoMoreVillager.instance.reloadConfig();
        fileConfiguration = NoMoreVillager.instance.getConfig();

        m_Limit = fileConfiguration.getInt("Limit", 3);
        m_ForceDelete = fileConfiguration.getInt("ForceDelete", 6);
    }

    public Integer getLimit() {
        return m_Limit;
    }
    public void setLimit(Integer limit) {
        m_Limit = limit;
        fileConfiguration.set("Limit", limit);
        NoMoreVillager.instance.saveConfig();
    }

    public Integer getForceDelete(){
        return m_ForceDelete;
    }
    public void setForceDelete(Integer forceDelete){
        m_ForceDelete = forceDelete;
        fileConfiguration.set("ForceDelete", forceDelete);
        NoMoreVillager.instance.saveConfig();
    }

    private Integer m_Limit;
    private Integer m_ForceDelete;
    FileConfiguration fileConfiguration;

}
