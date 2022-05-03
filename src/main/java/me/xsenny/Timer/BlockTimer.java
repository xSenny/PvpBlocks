package me.xsenny.pvpblocks.Timer;

import me.xsenny.pvpblocks.Blocks.Blocks;
import me.xsenny.pvpblocks.Blocks.BlocksMethod;
import me.xsenny.pvpblocks.PvPBlocks;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockTimer extends BukkitRunnable {

    public PvPBlocks plugin = PvPBlocks.plugin;

    @Override
    public void run() {
        for (Blocks blocks : BlocksMethod.blocksList){
            if (blocks.getTimer() > 0){
                blocks.setTimer(blocks.getTimer() - 1);
            }else{
                blocks.getLocation().getBlock().setType(blocks.getMaterial());
            }
        }
    }
}
