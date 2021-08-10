package com.archmageinc.playerlocations.plugin.infohandlers;

import com.archmageinc.playerlocations.plugin.PlayerLocations;
import com.archmageinc.playerlocations.plugin.info.PlayerInfo;
import com.archmageinc.playerlocations.plugin.info.PositionInfo;
import com.archmageinc.playerlocations.plugin.utils.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.archmageinc.playerlocations.api.InfoHandler;

public class PlayerInfoHandler implements InfoHandler {
    PlayerLocations plugin;
    
    public PlayerInfoHandler(PlayerLocations plugin) {
        this.plugin = plugin;
    }
    
    
    @Override
    public Map<String, Object> getInfo() {
        Map<String, Object> map = new HashMap();
        List<PlayerInfo> players = new ArrayList();
        
        map.put("players", players);
        
        plugin.getServer().getOnlinePlayers().forEach(player -> {
            PositionInfo position = (new PositionInfo())
                .setDimension(Dimension.getNamespace(player.getLocation()))
                .setX(player.getLocation().getX())
                .setY(player.getLocation().getY())
                .setZ(player.getLocation().getZ()
            );
            
            players.add((new PlayerInfo())
                .setAir(player.getRemainingAir())
                .setHealth(player.getHealth())
                .setLevel(player.getLevel())
                .setFood(player.getFoodLevel())
                .setName(player.getDisplayName())
                .setPosition(position)
            );
        });
        return map;
    }
    
}
