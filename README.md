# PlayerLocations
A Minecraft plugin built using the Spigot API which sends player location information to a web socket. 

This plugin was created after numerous attempts to provide a vanilla Minecraft way of providing player location data to external sources.

See [minecraft-player-locations](https://github.com/ArchmageInc/minecraft-player-locations) and the branches therein. 
As it turns out, RCON is a terrible communication protocol for interacting with Minecraft. In addition, it requires more constant upkeep with Minecraft's command system. 

This works in conjunction with [Overviewer-Info-Plugin](https://github.com/ArchmageInc/Overviewer-Info-Plugin) which handles the front-end rendering of players on an overviewer map. 

## Usage

The PlayerLocations.jar file can be placed in the `plugins` directory of a [Spigot](https://www.spigotmc.org/), [PaperMC](https://papermc.io/), or other [Bukkit](https://dev.bukkit.org/) derivitave Minecraft server. 

## Configuration

```YAML
socket_server:
    port: 8888
    host: localhost
    tick_interval: 100
```

**socket_server.port**: The server port to listen for socket requests 

**socket_server.host**: The hostname to listen for socket requests

**socket_server.tick_interval**: How often, in server ticks, the information should be broadcasted to connected socket clients

## Output

The information sent through the web socket is JSON data. For an example of how to implement this on the client side, see the [Overviewer-Info-Plugin](https://github.com/ArchmageInc/Overviewer-Info-Plugin) mentioned above. 
The default output uses the following structure:
```JSON
{
    "webClients": int,
    "timeOfDay": int,
    "players":[
        {
            "name": str,
            "position": {
                "x": double,
                "y": double,
                "z": double,
                "dimension": str
            },
            "health": double,
            "level": int,
            "air": int,
            "food": int
        },
        ...
    ]
}
```

**NOTE**

There may be some inconsistency when using plugins which create additional worlds which use custom environments (dimensions). 
There is also a limitiation to how this is tied to Overviewer renders which must be overcome. 

## Extending

If you would like to add additional information to the socket using a custom plugin, add PlayerLocations to your plugin's dependency or soft dependency list in your `plugin.yml` file:

```YAML
name: YourPluginName
main: com.your.plugin.Plugin
version: 0.17
api-version: 1.17
depend: [PlayerLocations]
```

```YAML
softdepend: [PlayerLocations]
```


