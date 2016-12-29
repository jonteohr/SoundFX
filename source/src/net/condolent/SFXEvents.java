package net.condolent;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public class SFXEvents implements Listener {

	SFXMain plugin;
	
	public SFXEvents(SFXMain instance) {
		plugin = instance;
	}
	
	public FileConfiguration getConfig() {
		return plugin.getConfig();
	}
	
	public static Sound chat = Sound.ENTITY_EXPERIENCE_ORB_PICKUP;
	public static Sound join = Sound.BLOCK_ANVIL_HIT;
	public static Sound quit = Sound.BLOCK_ANVIL_HIT;
	public static Sound respawn = Sound.BLOCK_STONE_BUTTON_CLICK_ON;
	public static Sound tp = Sound.BLOCK_PORTAL_TRAVEL;
	
	@EventHandler
	public void onMsgSound(AsyncPlayerChatEvent e) {
		/*Player p = (Player) Bukkit.getOnlinePlayers();
		Location location = p.getLocation();*/

		if(getConfig().getBoolean("chatSound")) {
			for(Player allPlayers : Bukkit.getOnlinePlayers()) {
				allPlayers.playSound(allPlayers.getLocation(), chat, 1, 0);
			}
		} else {
			// Do nothing
		}
	}
	
	// Play sound on player join
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		// Play a sound on join?
		if(getConfig().getBoolean("playerJoinSound")) {
			for(Player allPlayers : Bukkit.getOnlinePlayers()) {
				allPlayers.playSound(allPlayers.getLocation(), join, 1, 0);
			}
		} else {
			// Do nothing
		}
	}
	
	// Play sound on player leave
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		
		if(getConfig().getBoolean("playerQuitSound")) {
			
			for(Player allPlayers : Bukkit.getOnlinePlayers()) {
				
				allPlayers.playSound(allPlayers.getLocation(), quit, 1, 0);
				
			}
			
		} else {
			// Do nothing
		}
		
	}
	
	// Play sound on teleport
	@EventHandler
	public void onTp(PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		
		if(e.getCause() == TeleportCause.COMMAND || e.getCause() == TeleportCause.PLUGIN) {
			if(getConfig().getBoolean("playerTP")) {
				p.playSound(p.getLocation(), tp, 1, 0);
			} else {
				// Do nothing
			}
		} else {
			// Do nothing
		}
	}
	
	// Play sound on respawn
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		
		if(getConfig().getBoolean("playerRespawn")) {
			
			// Play respawn sound to everyone
			if(getConfig().getString("respawnSound").equalsIgnoreCase("all")) {
				
				for(Player p : Bukkit.getOnlinePlayers()) {
					p.playSound(p.getLocation(), respawn, 1, 0);
				}
				
			} else if(getConfig().getString("respawnSound").equalsIgnoreCase("player")) {
				Player p = e.getPlayer();
				
				p.playSound(p.getLocation(), respawn, 1, 0);
			}
			
		} else {
			// Do nothing
		}
		
	}
}
