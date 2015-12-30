package me.iamguus.soulwell.listeners;

import me.iamguus.soulwell.inventory.MainInventory;
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
        System.out.println("check0");
        if (event.getAction().name().contains("BLOCK")) {
            System.out.println("check1");
            if (player.getItemInHand() != null) {
                System.out.println("check2");
                ItemStack item = player.getItemInHand();
                if (event.getClickedBlock() != null) {
                    System.out.println("check3");
                    Block clickedBlock = event.getClickedBlock();
                    if (clickedBlock.getType() == Material.ENDER_PORTAL_FRAME) {
                        System.out.println("check4");
                        if (BlocksUtil.get().getByLocation(clickedBlock.getLocation()) != null) {
                            System.out.println("check5");
                            if (item.getType() == Material.SKULL_ITEM) {
                                System.out.println("check6");
                                if (item.getDurability() == (short) 3) {
                                    System.out.println("check7");
                                    player.openInventory(MainInventory.get().getInventory());
                                    event.setCancelled(true);
                                    player.updateInventory();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
