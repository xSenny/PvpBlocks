package me.xsenny.pvpblocks.Events;

import me.kodysimpson.simpapi.colors.ColorTranslator;
import me.xsenny.pvpblocks.Blocks.Blocks;
import me.xsenny.pvpblocks.Blocks.BlocksMethod;
import me.xsenny.pvpblocks.PvPBlocks;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Beacon;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BreakEvent implements Listener {

    public static PvPBlocks plugin = PvPBlocks.plugin;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Block block = e.getBlock();
        Location loc = block.getLocation();
        Player p = e.getPlayer();
        if (BlocksMethod.isCreated(loc)) {
            Blocks blocks = BlocksMethod.getBlockByLocation(loc);
            if ((blocks.getTimer() == 0) || (blocks.getTimer() < 0)) {
                e.setCancelled(true);
                breakBlock(loc);
            } else {
                e.setCancelled(true);
                p.sendMessage(ColorTranslator.translateColorCodes("&aMai ai de asteptat &7"+blocks.getTimer()+" &asecunde"));
            }
        }
    }


    public void breakBlock(Location location){
        BlocksMethod.getBlockByLocation(location).setTimer(plugin.getConfig().getInt("Countdown"));
        new Location(location.getWorld(), location.getX(), location.getY(), location.getZ()).getBlock().setType(Material.valueOf(
                plugin.getConfig().getString("Block")
        ));
    }

}
