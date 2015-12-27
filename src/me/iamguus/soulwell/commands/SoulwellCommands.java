package me.iamguus.soulwell.commands;

import me.iamguus.soulwell.SoulBlock;
import me.iamguus.soulwell.utils.BlocksUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * Created by Guus on 26-12-2015.
 */
public class SoulwellCommands implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You are not allowed to execute this command since you are not a player!");
            return false;
        }

        Player player = (Player) sender;
        //soulwell create || permission = soulwell.admin
        if (cmd.getName().equalsIgnoreCase("soulwell")) {
            if (player.hasPermission("soulwell.admin")) {
                //create command
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("create")) {
                        Block targetBlock = player.getTargetBlock((Set<Material>) null, 8);
                        if (targetBlock.getType() == Material.ENDER_PORTAL_FRAME) {
                            SoulBlock block = new SoulBlock(BlocksUtil.get().getNewID(), targetBlock);
                            BlocksUtil.get().createHologram(targetBlock);
                            block.save();
                            player.sendMessage(ChatColor.GREEN + "You successfully created a new SoulWell!");
                            return true;
                        } else {
                            player.sendMessage(ChatColor.RED + "The block you are looking at is not an Ender Portal Frame!");
                            return false;
                        }
                    } else
                    if (args[0].equalsIgnoreCase("remove")) {
                        Block targetBlock = player.getTargetBlock((Set<Material>) null, 8);
                        if (targetBlock.getType() == Material.ENDER_PORTAL_FRAME) {
                            BlocksUtil.get().remove(targetBlock.getLocation());
                        } else {
                            player.sendMessage(ChatColor.RED + "The block you are looking at is not an Ender Portal Frame!");
                            return false;
                        }
                    }
                } else if (args.length == 0) {
                    //TODO: Send command list
                }
            } else {
                player.sendMessage("You do not have the permission to execute this command!");
                return false;
            }
        }
        return false;
    }
}
