package me.xsenny.pvpblocks.Commands;

import me.kodysimpson.simpapi.colors.ColorTranslator;
import me.xsenny.pvpblocks.Blocks.BlocksMethod;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;


public class ManageBlocks implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;
            Block  block  = p.getTargetBlockExact(5);

            if (p.hasPermission("pvpblocks.manage")){
                if (block != null){
                    Location location = block.getLocation();
                    if (BlocksMethod.isCreated(location)){
                        try {
                            BlocksMethod.RemoveBlocks(location);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        p.sendMessage(ColorTranslator.translateColorCodes("&4Ai sters un PvPBlock creat mai devreme"));
                    }else{
                        BlocksMethod.CreateBlocks(location, block.getType());
                        p.sendMessage(ColorTranslator.translateColorCodes("&bAi creat un nou PvPBlock"));
                    }
                }else{
                    p.sendMessage(ColorTranslator.translateColorCodes("&cIncearca sa te uiti la un block mai apropiat"));
                }
            }else{
                p.sendMessage(ColorTranslator.translateColorCodes("&cNu ai permisiune pentru a utiliza aceasta comanda: &7pvpblocks.manage&f."));
            }
        }else{
            System.out.println("Aceasta comanda este destinata doar unui jucator");
        }


        return true;
    }

}
