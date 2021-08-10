package com.archmageinc.playerlocations;

import com.archmageinc.playerlocations.tasks.ServerRetryTask;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.server.WebSocketServer;

public class SocketServer extends WebSocketServer {
    PlayerLocations plugin;
    InetSocketAddress address;
    
    /**
     * Creates a WebSocketServer that will attempt to bind/listen on the given <var>address</var>
     * 
     * @param plugin The instance of the plugin responsible for the socket server
     * @param address The address to listen to
     */
    public SocketServer(PlayerLocations plugin, InetSocketAddress address) {
        super(address);
        this.address = address;
        this.plugin = plugin;
    }
    
    @Override
    public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(WebSocket conn, Draft draft, ClientHandshake request) throws InvalidDataException {
        ServerHandshakeBuilder builder = super.onWebsocketHandshakeReceivedAsServer(conn, draft, request);
        builder.put("Server", "Minecraft Player Locations");
        return builder;
    }
    
    @Override
    public void onOpen(WebSocket ws, ClientHandshake ch) {
        
    }

    @Override
    public void onClose(WebSocket ws, int i, String string, boolean bln) {
        
    }

    @Override
    public void onMessage(WebSocket ws, String string) {
        
    }

    @Override
    public void onError(WebSocket ws, Exception excptn) {
        
    }

    @Override
    public void onStart() {
        plugin.getLogger().info(String.format("Socket server started on %s:%s", getAddress().getHostString(), address.getPort()));
    }
    
    /**
     * Checks if the SocketServer is able to bind to the given address. If not, schedule a task to try again.
     */
    @Override
    public void start(){
        if (portAvailable(getAddress().getPort())) {
            super.start();
        }else {
            this.plugin.getLogger().warning("Unable to start socket server, address in use. Waiting to try again.");
            (new ServerRetryTask(this)).runTaskLater(plugin, 200);
        }
    }
    
    public int getClientCount(){
        return getConnections().size();
    }
    
    private boolean portAvailable(int port) {
        ServerSocket ss = null;
        DatagramSocket ds = null;
        try {
            ss = new ServerSocket(port);
            ss.setReuseAddress(true);
            ds = new DatagramSocket(port);
            ds.setReuseAddress(true);
            return true;
        } catch (IOException e) {
        } finally {
            if (ds != null) {
                ds.close();
            }

            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    plugin.getLogger().severe("There was an error attempting to close test socket!");
                }
            }
        }

        return false;
    }
    
}
