package com.archmageinc.playerlocations.datahandlers;

import com.archmageinc.playerlocations.DataHandler;
import com.archmageinc.playerlocations.PlayerLocations;
import com.archmageinc.playerlocations.info.PlayerInfo;
import com.archmageinc.playerlocations.info.PositionInfo;
import com.archmageinc.playerlocations.utils.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerDataHandler implements DataHandler {
    PlayerLocations plugin;
    
    public PlayerDataHandler(PlayerLocations plugin) {
        this.plugin = plugin;
    }
    
    
    @Override
    public Map<String, Object> getData() {
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
