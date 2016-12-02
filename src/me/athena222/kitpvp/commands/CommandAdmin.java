package me.athena222.kitpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.athena222.kitpvp.Main;
import me.athena222.kitpvp.util.Utils;

public class CommandAdmin implements CommandExecutor {

	public String prefix = ChatColor.DARK_RED + "Admin " + ChatColor.DARK_GRAY + "> " + ChatColor.RED + "";
	
	public Main plugin;
	
	public CommandAdmin(Main instance) {
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) { 
		if(!(sender instanceof Player)) {
			sender.sendMessage("Console cannot use KitPvP commands!");
			return true;
		}
		
		Player player = (Player) sender;
		
		if(args.length == 0) {
			if(cmd.getName().equalsIgnoreCase("admin")) { // also check for permissions
				player.sendMessage("");
				player.sendMessage("KitPvP Administration Panel");
				player.sendMessage("");
				player.sendMessage("/admin resetsuffix <player> " + ChatColor.GRAY + "Resets the specified players suffix.");
				player.sendMessage("/admin setkills <player> <kills> " + ChatColor.GRAY + "Sets the amount of kills of the specified player.");
				player.sendMessage("/admin setlevel <player> <level> " + ChatColor.GRAY + "Sets the level of the specified player.");
				player.sendMessage("/admin setkit <player> <kit> " + ChatColor.GRAY + "Sets the kit of the specified player.");
				player.sendMessage("/admin reload " + ChatColor.GRAY + "Reloads all files.");
				player.sendMessage("");
			}
		}
		
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("resetsuffix")) {
				player.sendMessage(prefix + "Invalid Arguments! /admin resetsuffix <player>");
			}
			else if(args[0].equalsIgnoreCase("reload")) {
				plugin.reloadConfig();
				plugin.reloadData();
				player.sendMessage(prefix + "Successfully reloaded files.");
			}
			else if(args[0].equalsIgnoreCase("setkills")) {
				player.sendMessage(prefix + "Invalid Arguments! /admin setkills <player> <kills>");
			}
			else if(args[0].equalsIgnoreCase("setlevel")) {
				player.sendMessage(prefix + "Invalid Arguments! /admin setlevel <player> <level>");
			}
			else if(args[0].equalsIgnoreCase("setkit")) {
				player.sendMessage(prefix + "Invalid Arguments! /admin setkit <player> <kit>");
			}
		}
		
		if(args.length == 2) {
			Player target = Bukkit.getPlayerExact(args[1]);
			int kills = Integer.parseInt(args[1]);			
			int level = Integer.parseInt(args[1]);
			
			if(target == null) {
				player.sendMessage(ChatColor.RED + "Couldn't find the specified player!");
			}
			
			if(args[0].equalsIgnoreCase("resetsuffix") && (args[1].equalsIgnoreCase(target.getName()))) {
				Utils.resetSuffix(target);
				player.sendMessage(prefix + "Reset " + target.getName() + "'s current suffix.");
			}
			else if(args[0].equalsIgnoreCase("setkills") && (args[1].equalsIgnoreCase(target.getName()))) {
				Utils.setKills(player, kills);
				player.sendMessage(prefix + "Successfully set " + target.getName() + "'s kills to " + kills + "!");
				target.sendMessage(ChatColor.GRAY + "An administrator has set your amount of kills to " + kills + "!");
			}
			else if(args[0].equalsIgnoreCase("setlevel") && (args[1].equalsIgnoreCase(target.getName()))) {
				Utils.setLevel(player, level);
				player.sendMessage(prefix + "Successfully set " + target.getName() + "'s level to " + level + "!");
				target.sendMessage(ChatColor.GRAY + "An administrator has set your kitpvp level to " + level + "!");
			}
		}
		
		if(args.length == 3) {
			Player target = Bukkit.getPlayerExact(args[1]);
			
			if(target == null) {
				player.sendMessage(ChatColor.RED + "Couldn't find the specified player!");
			} 
			
			if(args[0].equalsIgnoreCase("setkit") && (args[1].equalsIgnoreCase(target.getName())) && (args[2].equalsIgnoreCase("archer"))) {
				Utils.setKit(player, "Archer");
				player.sendMessage(prefix + "Successfully set " + target.getName() + "'s kit to Archer!");
				target.sendMessage(ChatColor.GRAY + "An administrator has set your kit to Archer!");
			}
			else if(args[0].equalsIgnoreCase("setkit") && (args[1].equalsIgnoreCase(target.getName())) && (args[2].equalsIgnoreCase("mage"))) {
				Utils.setKit(player, "Mage");
				player.sendMessage(prefix + "Successfully set " + target.getName() + "'s kit to Mage!");
				target.sendMessage(ChatColor.GRAY + "An administrator has set your kit to Mage!");
			}
			else if(args[0].equalsIgnoreCase("setkit") && (args[1].equalsIgnoreCase(target.getName())) && (args[2].equalsIgnoreCase("swordsman"))) {
				Utils.setKit(player, "Swordsman");
				player.sendMessage(prefix + "Successfully set " + target.getName() + "'s kit to Swordsman!");
				target.sendMessage(ChatColor.GRAY + "An administrator has set your kit to Swordsman!");
			}
			else {
				player.sendMessage(prefix + "Something went wrong! Check your spelling, and/or if it was a valid kit.");
			}
		}		
		return true;
	}	
}