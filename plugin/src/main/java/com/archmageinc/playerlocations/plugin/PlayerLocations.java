package com.archmageinc.playerlocations.plugin;

import com.archmageinc.playerlocations.api.InfoRegistrar;
import com.archmageinc.playerlocations.api.InfoHandler;
import com.archmageinc.playerlocations.plugin.infohandlers.PlayerInfoHandler;
import com.archmageinc.playerlocations.plugin.infohandlers.ServerInfoHandler;
import com.archmageinc.playerlocations.plugin.tasks.InfoTask;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class PlayerLocations extends JavaPlugin implements InfoRegistrar{
    
    SocketServer socketServer;
    Set<InfoHandler> dataHandlers = new HashSet();
    InfoTask infoTask;
    
    @Override
    public void onEnable() {
        saveDefaultConfig();
        socketServer = new SocketServer(this, new InetSocketAddress(getConfig().getString("socket_server.host"), getConfig().getInt("socket_server.port")));
        infoTask = new InfoTask(this, socketServer);
        socketServer.start();
        registerInfoHandler(new ServerInfoHandler(this, socketServer));
        registerInfoHandler(new PlayerInfoHandler(this));
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
    
    @Override
    public void registerInfoHandler(InfoHandler handler) {
        dataHandlers.add(handler);
    }
    
    @NotNull
    @Override
    public Set<InfoHandler> getInfoHandlers() {
        return dataHandlers;
    }
}
