package com.archmageinc.playerlocations;

import com.archmageinc.playerlocations.datahandlers.PlayerDataHandler;
import com.archmageinc.playerlocations.datahandlers.ServerDataHandler;
import com.archmageinc.playerlocations.tasks.InfoTask;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class PlayerLocations extends JavaPlugin {
    
    SocketServer socketServer;
    Set<DataHandler> dataHandlers = new HashSet();
    InfoTask infoTask;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        socketServer = new SocketServer(this, new InetSocketAddress(getConfig().getString("socket_server.host"), getConfig().getInt("socket_server.port")));
        infoTask = new InfoTask(this, socketServer);
        socketServer.start();
        registerDataHandler(new ServerDataHandler(this, socketServer));
        registerDataHandler(new PlayerDataHandler(this));
        infoTask.runTaskTimer(this, getConfig().getInt("socket_server.tick_interval", 100), getConfig().getInt("socket_server.tick_interval", 100));
    }
    
    @Override
    public void onDisable() {
        if(socketServer != null) {
            try {
                socketServer.stop();
            } catch (InterruptedException ex) {
                /* Intentionally ignored */
            }
        }
    }
    
    public void registerDataHandler(DataHandler handler) {
        dataHandlers.add(handler);
    }
    
    @NotNull
    public Set<DataHandler> getDataHandlers() {
        return dataHandlers;
    }
}
