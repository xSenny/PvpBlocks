package me.xsenny.pvpblocks.Blocks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.lightdream.lambda.LambdaExecutor;
import me.xsenny.pvpblocks.PvPBlocks;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class BlocksMethod {

    public static ArrayList<Blocks> blocksList = new ArrayList<>();
    public static PvPBlocks plugin = PvPBlocks.plugin;


    public static Blocks CreateBlocks(Location location, Material material) {

        Blocks blocks = new Blocks(0, location, material);
        blocksList.add(blocks);
        save(blocksList);
        return blocks;
    }

    public static void RemoveBlocks(Location location) throws IOException {
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        String world = location.getWorld().getName();
        for (Blocks blocks : blocksList) {
            double x1 = blocks.getLocation().getX();
            double y1 = blocks.getLocation().getY();
            double z1 = blocks.getLocation().getZ();
            String world1 = blocks.getLocation().getWorld().getName();
            if ((x == x1) && (y == y1) && (z == z1) && (world.equalsIgnoreCase(world1))) {
                blocksList.remove(blocks);
                save(blocksList);
                break;
            }
        }
    }

    public static boolean isCreated(Location location) {
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        String world = location.getWorld().getName();
        for (Blocks blocks : blocksList) {
            double x1 = blocks.getLocation().getX();
            double y1 = blocks.getLocation().getY();
            double z1 = blocks.getLocation().getZ();
            String world1 = blocks.getLocation().getWorld().getName();
            if ((x == x1) && (y == y1) && (z == z1) && (world.equalsIgnoreCase(world1))) {
                return true;
            }
        }
        return false;
    }




    public static void save(Object object) {
        Gson gson;
        GsonBuilder gsonBuilder;
        gsonBuilder = new GsonBuilder().setPrettyPrinting();
        gson = gsonBuilder.create();
        LambdaExecutor.LambdaCatch.NoReturnLambdaCatch.executeCatch(() -> {
            String json = gson.toJson(object);
            String path = plugin.getDataFolder().getAbsolutePath()+"/BlockStorage.json";

            //Create folders
            new File(path).getParentFile().mkdirs();

            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(json);

            writer.close();
        });
    }

    public static void loadBlocks() throws IOException {

        Gson gson = new Gson();
        File file = new File(plugin.getDataFolder().getAbsolutePath() + "/BlockStorage.json");
        if (file.exists()){
            Reader reader = new FileReader(file);
            Blocks[] n = gson.fromJson(reader, Blocks[].class);
            blocksList = new ArrayList<>(Arrays.asList(n));
            System.out.println("S-au incarcat block-urile salvate");
        }

    }

    public static Blocks getBlockByLocation(Location location){
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        String world = location.getWorld().getName();
        for (Blocks blocks : blocksList) {
            double x1 = blocks.getLocation().getX();
            double y1 = blocks.getLocation().getY();
            double z1 = blocks.getLocation().getZ();
            String world1 = blocks.getLocation().getWorld().getName();
            if ((x == x1) && (y == y1) && (z == z1) && (world.equalsIgnoreCase(world1))) {
                return blocks;
            }
        }
        return null;
    }


}
