package com.archmageinc.playerlocations.tasks;

import com.archmageinc.playerlocations.PlayerLocations;
import com.archmageinc.playerlocations.SocketServer;
import com.archmageinc.playerlocations.info.Info;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import org.bukkit.scheduler.BukkitRunnable;

public class InfoTask extends BukkitRunnable {
    PlayerLocations plugin;
    SocketServer socketServer;
    
    public InfoTask(PlayerLocations plugin, SocketServer socketServer) {
        this.plugin = plugin;
        this.socketServer = socketServer;
    }
    
    @Override
    public void run() {
        if(socketServer.getClientCount() < 1) {
            return;
        }
        Info info = new Info();
        
        plugin.getDataHandlers().forEach(dataHandler -> {
            info.putAll(dataHandler.getData());
        });
        
        sendToSocket(info);
    }
    
    private void sendToSocket(Info info) {
        try {
            String json = (new ObjectMapper()).writeValueAsString(info);
            socketServer.broadcast(json);
        } catch (JsonProcessingException ex) {
            plugin.getLogger().log(Level.SEVERE, "Error while converting json data." , ex);
        }
    }
}
