package me.athena222.kitpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.athena222.kitpvp.util.Utils;

public class CommandAdmin implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Console cannot use KitPvP commands!");
			return true;
		}
		
		Player player = (Player) sender;
		
		if(args.length == 0) {
			if(cmd.getName().equalsIgnoreCase("admin")) {
				player.sendMessage("");
				player.sendMessage("KitPvP Administration Panel");
				player.sendMessage("");
				player.sendMessage("/admin resetsuffix <player> " + ChatColor.GRAY + "Resets the specified players suffix.");
				player.sendMessage("/admin setkills <player> " + ChatColor.GRAY + "Sets the amount of kills of the specified player.");
				player.sendMessage("/admin setlevel <player> " + ChatColor.GRAY + "Sets the level of the specified player.");
				player.sendMessage("/admin reload " + ChatColor.GRAY + "Reloads all files.");
				player.sendMessage("");
			}
		}
		
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("resetsuffix")) {
				
			}
			else if(args[0].equalsIgnoreCase("reload")) {
				
			}
			else if(args[0].equalsIgnoreCase("setkills")) {
				
			}
			else if(args[0].equalsIgnoreCase("setlevel")) {
				
			}
		}
		
		if(args.length == 2) {
			Player target = Bukkit.getPlayerExact(args[1]);
			
			if(args[0].equalsIgnoreCase("resetsuffix") && (args[1].equals(target.getName()))) {
				Utils.resetSuffix(target);
				// send message, also check for permissions
			}
		}
		
		return true;
	}	
}
// remove suffix, setkills,  etc etc - make this in utils?!
// reload cmd as well? core?