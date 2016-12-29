# SoundFX
## Description
SoundFX is a plugin for Bukkit. A software running Minecraft servers.  
This plugin gives your server a more unique feel, with sound effects for different events.

You can disable and enable every sound in the configuration file, and soon you will be able to decide what sound you want played all by yourself in the configuration aswell!

## Events that gives a sound effect
Currently, there's X total events that you can enable a sound effect for. They are as follows:
* New chat messages
* Player join
* Player leave
* Player respawns
* Player teleports (via command)

And as per usual; you can always come with feedback on other events you'd like to be added to the plugin via the [Issues](https://github.com/condolent/SoundFX/issues) page

## Permissions
There's only one single permission to keep it all clean and simple, which is the admin permission.

Node | Description
--- | ---
sfx.admin | Ability to use admin-specific commands. Currently only the `/sfx reload` command.

## Commands
The base command of everything in this plugin is `/sfx`. You can type that and get a short list of all available commands.  
Here's also a list to make it simple:

Command | Description | Access
--- | --- | ---
/sfx | Gives a minor list of all available commands. | All
/sfx version | Returns the current version of _SoundFX_ the server is running. | All
/sfx info | Some info about the plugin and it's origin. | All
/sfx reload | The command to reload the config, giving you the opportunity to change config-variables real-time! | Admins
/sfx preview <event> | Allows you to preview the sound effect applied for the given event. The event-names are: `join`, `quit`, `respawn`, `tp` and `chat` | Admins

## Configuration
The plugin only has one configuration file, so it's fairly easy for you to setup and launch!
```
###############################
#                             #
#           SoundFX           #
#                             #
###############################

# Play a sound to all when player joins.
playerJoinSound: true

# Play a sound to all when a player quits.
playerQuitSound: true

# Play sound to all when someone sends a message
chatSound: true

# Play a sound to the player when a player uses a /tp command
playerTP: true
# Play a sound when a player respawns
playerRespawn: false

# If playerRespawn is true; play the respawn sound to all or just the specific player
# all = all players hear, player = only the respawning player.
respawnSound: 'player'```

## To-do
* Option to configure your own sound effect for each event
* More events
