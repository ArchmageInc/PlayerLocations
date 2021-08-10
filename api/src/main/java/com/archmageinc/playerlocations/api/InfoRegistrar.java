package com.archmageinc.playerlocations.api;

import java.util.Set;
import org.jetbrains.annotations.NotNull;

public interface InfoRegistrar {

    /**
     * Registers an Info Handler which will be called each cycle to get information to be sent to the web socket
     * 
     * @param handler The InfoHandler object
     */
    public void registerInfoHandler(@NotNull InfoHandler handler);
    
    /**
     * 
     * @return Returns the set of registered DataHandlers
     */
    @NotNull
    public Set<InfoHandler> getInfoHandlers();
}
