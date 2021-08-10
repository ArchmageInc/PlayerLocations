package com.archmageinc.playerlocations;

import java.util.Map;
import org.jetbrains.annotations.NotNull;

/**
 *
 * Represents an object responsible for producing additional data to be included in the socket output
 */
public interface DataHandler {

    /**
     *
     * @return Returns a map of keys and values to be added to the socket output
     */
    @NotNull
    public Map<String, Object> getData();
}
