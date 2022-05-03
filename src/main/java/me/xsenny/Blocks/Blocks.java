package me.xsenny.pvpblocks.Blocks;

import org.bukkit.Location;
import org.bukkit.Material;

public class Blocks {

    private int timer;
    private GsonLocation location;
    private Material material;


    public Blocks(int timer, Location location, Material material){
        this.timer = timer;
        this.location = new GsonLocation(location);
        this.material = material;
    }


    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public Location getLocation() {
        return location.toLocation();
    }

    public void setLocation(Location location) {
        this.location = new GsonLocation(location);
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
