package me.xsenny.pvpblocks;

import me.xsenny.pvpblocks.Blocks.BlocksMethod;
import me.xsenny.pvpblocks.Commands.ManageBlocks;
import me.xsenny.pvpblocks.Events.BreakEvent;
import me.xsenny.pvpblocks.Timer.BlockTimer;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.IOException;

public final class PvPBlocks extends JavaPlugin {


    public static PvPBlocks plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Se incarca pluginul");
        plugin = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("block").setExecutor(new ManageBlocks());
        BukkitTask task = new BlockTimer().runTaskTimer(this, 0L, 20L);
        getServer().getPluginManager().registerEvents(new BreakEvent(), this);

        try {
            BlocksMethod.loadBlocks();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("S-a terminat incarcarea pluginului.");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        BlocksMethod.save(BlocksMethod.blocksList);
    }
}
