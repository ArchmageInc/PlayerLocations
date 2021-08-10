package com.archmageinc.playerlocations.datahandlers;

import com.archmageinc.playerlocations.DataHandler;
import com.archmageinc.playerlocations.PlayerLocations;
import com.archmageinc.playerlocations.SocketServer;
import java.util.HashMap;
import java.util.Map;

public class ServerDataHandler implements DataHandler{
    PlayerLocations plugin;
    SocketServer socketServer;
    
    public ServerDataHandler(PlayerLocations plugin, SocketServer socketServer) {
        this.plugin = plugin;
        this.socketServer = socketServer;
    }
    
    @Override
    public Map<String, Object> getData() {
        Map<String, Object> map = new HashMap();
        map.put("timeOfDay", plugin.getServer().getWorlds().get(0).getTime());
        map.put("webClients", socketServer.getClientCount());
        
        return map;
    }
    
}
