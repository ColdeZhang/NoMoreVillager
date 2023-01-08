package site.deercloud.nomorevillager;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.TradeSelectEvent;
import org.bukkit.inventory.Merchant;

import java.util.ArrayList;
import java.util.Objects;

public class Events implements Listener {

    @EventHandler
    public void onBreed(EntityBreedEvent event) {
        if (!(event.getEntity() instanceof Villager)) {
            return;
        }
        Villager baby = (Villager) event.getEntity();
        Location location = baby.getLocation();
        Chunk chunk = location.getChunk();
        ArrayList<Villager> villagers = new ArrayList<>();
        // 遍历周围的区块 
        for (int x = -4; x <= 4; x++) {
            for (int z = -4; z <= 4; z++) {
                Chunk chunk1 = chunk.getWorld().getChunkAt(chunk.getX() + x, chunk.getZ() + z);
                Entity[] entities = chunk1.getEntities();
                for (Entity entity : entities) {
                    if (entity instanceof Villager) {
                        Villager villager = (Villager) entity;
                        if (villager.getProfession() == Villager.Profession.NONE || !villager.isAdult()) {
//                            NoMoreVillager.instance.getLogger().info("搜索到一个无业或未成年");
                            villagers.add((Villager) entity);
                        }
                    }
                }
            }
        }
        // 达到强制执行最大值就开始删除
        if (villagers.size() >= NoMoreVillager.configManager.getForceDelete()) {
//            NoMoreVillager.instance.getLogger().info("开始清除");
            int kill_number = villagers.size() - NoMoreVillager.configManager.getForceDelete();
            for (int i = 0; i < kill_number; i++) {
//                NoMoreVillager.instance.getLogger().info("清除了一个");
                Villager villager = villagers.get(i);
                villager.setHealth(0.0);
            }
        }
        // 如果达到上限就禁止繁殖
        if (villagers.size() >= NoMoreVillager.configManager.getLimit()) {
//            NoMoreVillager.instance.getLogger().info("超出限额禁止繁殖");
            ((Villager) event.getFather()).getInventory().clear();
            ((Villager) event.getMother()).getInventory().clear();

            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerTrade(TradeSelectEvent event) {
//        NoMoreVillager.instance.getLogger().info("产生了交易行为");
        Merchant merchant = event.getMerchant();
        Location location = Objects.requireNonNull(merchant.getTrader()).getLocation();
        Chunk chunk = location.getChunk();
        ArrayList<Villager> villagers = new ArrayList<>();
        // 遍历周围的区块
        Entity[] entities = chunk.getEntities();
        for (Entity entity : entities) {
            if (entity instanceof Villager) {
                Villager villager = (Villager) entity;
                if (villager.getProfession() == Villager.Profession.NONE || !villager.isAdult()) {
//                            NoMoreVillager.instance.getLogger().info("搜索到一个无业或未成年");
                    villagers.add((Villager) entity);
                }
            }
        }
        // 达到强制执行最大值就开始删除
        if (villagers.size() >= NoMoreVillager.configManager.getForceDelete()) {
//            NoMoreVillager.instance.getLogger().info("开始清除");
            int kill_number = villagers.size() - NoMoreVillager.configManager.getForceDelete();
            for (int i = 0; i < kill_number; i++) {
//                NoMoreVillager.instance.getLogger().info("清除了一个");
                Villager villager = villagers.get(i);
                villager.setHealth(0.0);
            }
        }
    }
}
