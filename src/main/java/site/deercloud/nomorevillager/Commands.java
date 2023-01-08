package site.deercloud.nomorevillager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Commands implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args[0]) {
            case "limit":
                if (args.length == 1) {
                    sender.sendMessage("§c请输入要设置的村民上限");
                    return true;
                }
                if (sender.isOp() || !(sender instanceof Player)) {
                    try {
                        Integer limit = Integer.parseInt(args[1]);
                        NoMoreVillager.configManager.setLimit(limit);
                        sender.sendMessage("§a村民繁殖上限已设置为 " + limit);
                    } catch (NumberFormatException e) {
                        sender.sendMessage("§c请输入正确的数字");
                    }
                } else {
                    sender.sendMessage("§c你没有权限执行此命令");
                }
                break;
            case "forceDelete":
                if (args.length == 1) {
                    sender.sendMessage("§c请输入要设置的强制处决阈值");
                    return true;
                }
                if (sender.isOp() || !(sender instanceof Player)) {
                    try {
                        Integer forceDelete = Integer.parseInt(args[1]);
                        NoMoreVillager.configManager.setForceDelete(forceDelete);
                        sender.sendMessage("§a强制处决的开始数量已设置为 " + forceDelete);
                    } catch (NumberFormatException e) {
                        sender.sendMessage("§c请输入正确的数字");
                    }
                } else {
                    sender.sendMessage("§c你没有权限执行此命令");
                }
                break;
            case "reload":
                if (sender.isOp() || !(sender instanceof Player)) {
                    NoMoreVillager.configManager.reload();
                    sender.sendMessage("§a配置已重载");
                } else {
                    sender.sendMessage("§c你没有权限执行此命令");
                }
                break;
            default:
                sender.sendMessage("§c未知的命令");
                break;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("limit", "forceDelete", "reload");
        } else {
            return Collections.emptyList();
        }
    }
}
