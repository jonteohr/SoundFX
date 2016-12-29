package net.condolent;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class SFXMain extends JavaPlugin {

	public Permission admin = new Permission("sfx.admin");

	private static Plugin plugin;
	public static Plugin getPlugin() {
		return plugin;
	}
	
	public void onEnable() {
		getLogger().info("SFX enabled!");

		plugin = this;
		
		this.getServer().getPluginManager().registerEvents(new SFXEvents(this), this);

		this.getConfig().options().copyDefaults(false);
		saveDefaultConfig();

	}

	public void onDisable() {
		getLogger().info("Disabling SFX..");
		saveDefaultConfig();
	}
	
	private String prefix = ChatColor.BOLD + "" + ChatColor.GOLD + "[SFX] ";
	private String noAccess = prefix + ChatColor.RED + "Insufficient permissions.";
	private String sep = "-------------------------------";
	private String incorrectArg = prefix + ChatColor.RED + "Incorrect arguments.";

	PluginDescriptionFile pdf = this.getDescription();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		Location location = p.getLocation();

		if (cmd.getName().equalsIgnoreCase("sfx")) {
			
			if (args.length < 1 || args.length > 2) {
				p.sendMessage(sep);
				p.sendMessage(prefix + ChatColor.RED + "Not enough arguments..");
				p.sendMessage(prefix + ChatColor.YELLOW + "Correct usage: /sfx <argument>");
				p.sendMessage(prefix + ChatColor.YELLOW + "Available arguments: version, reload, info, preview");
				p.sendMessage(sep);
			} else if (args.length == 1) {
				
				if (args[0].equalsIgnoreCase("version")) {
					p.sendMessage(prefix + ChatColor.YELLOW + "This server is running version " + pdf.getVersion() + "of " + pdf.getName());
				}
				
				if (args[0].equalsIgnoreCase("reload")) {
					if (p.hasPermission(admin)) {
						reloadConfig();
						p.sendMessage(prefix + ChatColor.GREEN + "Config reloaded!");
					} else {
						p.sendMessage(noAccess);
					}
				}
				
				if(args[0].equalsIgnoreCase("info")) {
					p.sendMessage(sep);
					p.sendMessage(prefix + ChatColor.YELLOW + "This plugin was made by Condolent.");
					p.sendMessage(prefix + ChatColor.YELLOW + "Originally made for the EverFree server per request,");
					p.sendMessage(prefix + ChatColor.YELLOW + "then released to the public with permission!");
					p.sendMessage(prefix + ChatColor.AQUA + "http://twitter.com/hyprcsgo");
					p.sendMessage(sep);
				}
				
				if(args[0].equalsIgnoreCase("preview")) {
					p.sendMessage(prefix + ChatColor.RED + "You didn't specify a sound effect.");
					p.sendMessage(prefix + "Available effects are: join, quit, respawn, chat, teleport");
				}
				
				else if(!args[0].equalsIgnoreCase("reload") && !args[0].equalsIgnoreCase("version") && !args[0].equalsIgnoreCase("info")) {
					p.sendMessage(incorrectArg);
				}
				
			} else if(args.length == 2 && args[0].equalsIgnoreCase("preview")) {
				
				if(args[1].equalsIgnoreCase("join")) {
					p.playSound(location, SFXEvents.join, 1, 0);
				}
				
				if(args[1].equalsIgnoreCase("quit")) {
					p.playSound(location, SFXEvents.quit, 1, 0);
				}
				
				if(args[1].equalsIgnoreCase("respawn")) {
					p.playSound(location, SFXEvents.respawn, 1, 0);
				}
				
				if(args[1].equalsIgnoreCase("chat")) {
					p.playSound(location, SFXEvents.chat, 1, 0);
				}
				
				if(args[1].equalsIgnoreCase("teleport")) {
					p.playSound(location, SFXEvents.tp, 1, 0);
				}
				
				else if(!args[1].equalsIgnoreCase("join") && !args[1].equalsIgnoreCase("quit") && !args[1].equalsIgnoreCase("respawn") && !args[1].equalsIgnoreCase("chat") && !args[1].equalsIgnoreCase("teleport")) {
					p.sendMessage(prefix + ChatColor.RED + "Invalid sound effect.");
					p.sendMessage(prefix + "Available effects are: join, quit, respawn, chat, teleport");
				}
				
			}
			
			return true;
		}

		return false;
	}

}
