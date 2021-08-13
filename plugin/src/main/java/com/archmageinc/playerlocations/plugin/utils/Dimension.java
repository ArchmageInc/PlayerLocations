package com.archmageinc.playerlocations.plugin.utils;

import java.util.EnumMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.World;

public class Dimension {
    
    private static final Map<World.Environment, String> byEnvironment = new EnumMap<World.Environment, String>(World.Environment.class);
    
    public static String getNamespace(Location location) {
    if(byEnvironment.isEmpty()) {
        byEnvironment.put(World.Environment.NORMAL, "minecraft:overworld");
        byEnvironment.put(World.Environment.NETHER, "minecraft:the_nether");
        byEnvironment.put(World.Environment.THE_END, "minecraft:the_end");
    }
    return byEnvironment.get(location.getWorld().getEnvironment());
} 
}
