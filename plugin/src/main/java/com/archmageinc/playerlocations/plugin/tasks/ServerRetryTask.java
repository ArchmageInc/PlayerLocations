package com.archmageinc.playerlocations.plugin.tasks;

import com.archmageinc.playerlocations.plugin.SocketServer;
import org.bukkit.scheduler.BukkitRunnable;

public class ServerRetryTask extends BukkitRunnable {
    SocketServer socketServer;
    
    /**
     * Creates a runnable task to defer starting the socket server.
     * 
     * @param socketServer the instance of SocketServer to defer starting
     */
    public ServerRetryTask(SocketServer socketServer) {
        this.socketServer = socketServer;
    }
    
    @Override
    public void run() {
        socketServer.start();
    }
}
