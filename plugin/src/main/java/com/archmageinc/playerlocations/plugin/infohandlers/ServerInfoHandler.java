package com.archmageinc.playerlocations.plugin.infohandlers;

import com.archmageinc.playerlocations.plugin.PlayerLocations;
import com.archmageinc.playerlocations.plugin.SocketServer;
import java.util.HashMap;
import java.util.Map;
import com.archmageinc.playerlocations.api.InfoHandler;

public class ServerInfoHandler implements InfoHandler{
    PlayerLocations plugin;
    SocketServer socketServer;
    
    public ServerInfoHandler(PlayerLocations plugin, SocketServer socketServer) {
        this.plugin = plugin;
        this.socketServer = socketServer;
    }
    
    @Override
    public Map<String, Object> getInfo() {
        Map<String, Object> map = new HashMap();
        map.put("timeOfDay", plugin.getServer().getWorlds().get(0).getTime());
        map.put("webClients", socketServer.getClientCount());
        
        return map;
    }
    
}
