package me.iamguus.soulwell.listeners;

import me.iamguus.soulwell.utils.BlocksUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Guus on 27-12-2015.
 */
public class ClickListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().name().contains("BLOCK")) {
            if (player.getItemInHand() != null) {
                ItemStack item = player.getItemInHand();
                if (event.getClickedBlock() != null) {
                    Block clickedBlock = event.getClickedBlock();
                    if (clickedBlock.getType() == Material.ENDER_PORTAL_FRAME) {
                        if (BlocksUtil.get().getByLocation(clickedBlock.getLocation()) != null) {
                            if (item.getType() == Material.SKULL_ITEM) {
                                if (item.getDurability() == (short) 3) {
                                    //TODO: Open inventory
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
