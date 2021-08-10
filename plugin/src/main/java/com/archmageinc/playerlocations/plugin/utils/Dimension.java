package com.archmageinc.playerlocations.plugin.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

public enum Dimension {
    OVERWORLD("minecraft:overworld", World.Environment.NORMAL, Arrays.asList(new String[]{"overworld", "normal"})),
    NETHER("minecraft:the_nether", World.Environment.NETHER, Arrays.asList(new String[]{"nether", "the_nether", "hell"})),
    END("minecraft:the_end", World.Environment.THE_END, Arrays.asList(new String[]{"end", "the_end"}));
    
    private final String namespace;
    private final World.Environment environment;
    private final List<String> aliases;
    private static final Map<World.Environment, String> byEnvironment = new EnumMap<World.Environment, String>(World.Environment.class);
    private static final Map<String, World.Environment> byNamespace = new HashMap<String, World.Environment>();
    
    private Dimension (String namespace, World.Environment environment, List<String> aliases) {
        this.namespace = namespace;
        this.environment = environment;
        this.aliases = aliases;
    }
    
    public static String getNamespace(World.Environment environment) {
        return byEnvironment.get(environment);
    }
    public static String getNamespace(Location location) {
        return byEnvironment.get(location.getWorld().getEnvironment());
    }
    
    public static List<String> getNamespaces(){
        List<String> namespaces = new ArrayList<String>();
        for(Dimension d : values()){
            namespaces.add(d.namespace);
        }
        return namespaces;
    }
    
    public static World.Environment getEnvironment(String namespace) {
        return byNamespace.get(namespace);
    }
    
    public static World.Environment parseEnvironment(String name) {
        World.Environment env = byNamespace.get(name);
        if(env != null) {
            return env;
        }
        for(Dimension d : values()) {
            if(d.aliases.contains(name.toLowerCase())) {
                return d.environment;
            }
        }
        return null;
    }
    
    public static List<World.Environment> getEnvironments(){
        return Arrays.asList(World.Environment.values());
    }
    
    public static World getWorldFromEnvironment(Plugin plugin, World.Environment env) {
        for(World w : plugin.getServer().getWorlds()) {
            if (w.getEnvironment().equals(env)){
                return w;
            }
        }
        return null;
    }
    
    public static World parseWorld(Plugin plugin, String name) {
        return getWorldFromEnvironment(plugin, parseEnvironment(name));
    }

    static {
        for(Dimension d : values()) {
            byEnvironment.put(d.environment, d.namespace);
            byNamespace.put(d.namespace, d.environment);
        }
    }
}
