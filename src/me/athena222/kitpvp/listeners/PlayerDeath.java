package me.athena222.kitpvp.listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.athena222.kitpvp.Main;
import me.athena222.kitpvp.util.Utils;

public class PlayerDeath implements Listener {

	Main plugin;
	
	public PlayerDeath(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		if(e.getEntity() instanceof Player) {	
			
			//Player player = (Player) e.getEntity();
			Player killer = (Player) e.getEntity().getKiller();
			
			if(killer == null) {  // Handles Essentials deaths (skull)
				return;
			}
			
			// ^^^^ if killer is lightning
			
			ConfigurationSection cs = plugin.usersConfig.getConfigurationSection("users" + "." + killer.getUniqueId().toString());
			
// Zombie, Squid, Blaze, Guardian, Spider are the donator ranks + groups, hook into vault & check
// make donor coin kill amounts configurable

			cs.set("Kills", 1 + cs.getInt("Kills"));
			
			if(plugin.perms.playerInGroup(killer, "Squid")) {
				cs.set("Coins", 3 + cs.getInt("Coins"));
			}
			else if(plugin.perms.playerInGroup(killer, "Blaze")) {
				cs.set("Coins", 4 + cs.getInt("Coins"));
			}
			else if(plugin.perms.playerInGroup(killer, "Guardian")) {
				cs.set("Coins", 5 + cs.getInt("Coins"));
			}
			else if(plugin.perms.playerInGroup(killer, "Spider")) {
				cs.set("Coins", 6 + cs.getInt("Coins"));
			}
			else {			
				cs.set("Coins", 2 + cs.getInt("Coins"));
			}	
			plugin.saveData();	
			
			/*
			switch(cs.getInt("Kills")) {
				case 5:
				break;
			}
			*/
			
			if(cs.getInt("Kills") == 5) { // Level 1 - 5 Kills
				Utils.setLevel(killer, 1);
				killer.sendMessage("");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You are now " + ChatColor.YELLOW + "Level 1" + ChatColor.GOLD + "!");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You need " + ChatColor.YELLOW + "5" + ChatColor.GOLD + " more kills to get to " + ChatColor.YELLOW + "Level 2" + ChatColor.GOLD + "!"); 
				killer.sendMessage("");
				plugin.chat.setPlayerSuffix(killer, ChatColor.GRAY + " [" + ChatColor.YELLOW + "Level 1" + ChatColor.GRAY + "]");
			}
			if(cs.getInt("Kills") == 10) { // Level 2 - 10 Kills
				Utils.setLevel(killer, 2);
				killer.sendMessage("");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You are now " + ChatColor.YELLOW + "Level 2" + ChatColor.GOLD + "!");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You need " + ChatColor.YELLOW + "10" + ChatColor.GOLD + " more kills to get to " + ChatColor.YELLOW + "Level 3" + ChatColor.GOLD + "!"); 
				killer.sendMessage("");
				plugin.chat.setPlayerSuffix(killer, ChatColor.GRAY + " [" + ChatColor.YELLOW + "Level 2" + ChatColor.GRAY + "]");
			}
			if(cs.getInt("Kills") == 20) { // Level 3 - 20 Kills
				Utils.setLevel(killer, 3);
				killer.sendMessage("");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You are now " + ChatColor.YELLOW + "Level 3" + ChatColor.GOLD + "!");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You need " + ChatColor.YELLOW + "10" + ChatColor.GOLD + " more kills to get to " + ChatColor.YELLOW + "Level 4" + ChatColor.GOLD + "!"); 
				killer.sendMessage("");
				plugin.chat.setPlayerSuffix(killer, ChatColor.GRAY + " [" + ChatColor.YELLOW + "Level 3" + ChatColor.GRAY + "]");
			}
			if(cs.getInt("Kills") == 30) { // Level 4 - 30 Kills
				Utils.setLevel(killer, 4);
				killer.sendMessage("");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You are now " + ChatColor.YELLOW + "Level 4" + ChatColor.GOLD + "!");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You need " + ChatColor.YELLOW + "10" + ChatColor.GOLD + " more kills to get to " + ChatColor.YELLOW + "Level 5" + ChatColor.GOLD + "!"); 
				killer.sendMessage("");
				plugin.chat.setPlayerSuffix(killer, ChatColor.GRAY + " [" + ChatColor.YELLOW + "Level 4" + ChatColor.GRAY + "]");
			}
			if(cs.getInt("Kills") == 40) { // Level 5 - 40 Kills
				Utils.setLevel(killer, 5);
				killer.sendMessage("");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You are now " + ChatColor.YELLOW + "Level 5" + ChatColor.GOLD + "!");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You need " + ChatColor.YELLOW + "10" + ChatColor.GOLD + " more kills to get to " + ChatColor.YELLOW + "Level 6" + ChatColor.GOLD + "!"); 
				killer.sendMessage("");
				plugin.chat.setPlayerSuffix(killer, ChatColor.GRAY + " [" + ChatColor.YELLOW + "Level 5" + ChatColor.GRAY + "]");
			}
			if(cs.getInt("Kills") == 50) { // Level 6 - 50 Kills
				Utils.setLevel(killer, 6);
				killer.sendMessage("");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You are now " + ChatColor.YELLOW + "Level 6" + ChatColor.GOLD + "!");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You need " + ChatColor.YELLOW + "10" + ChatColor.GOLD + " more kills to get to " + ChatColor.YELLOW + "Level 7" + ChatColor.GOLD + "!"); 
				killer.sendMessage("");
				plugin.chat.setPlayerSuffix(killer, ChatColor.GRAY + " [" + ChatColor.YELLOW + "Level 6" + ChatColor.GRAY + "]");
			}
			if(cs.getInt("Kills") == 60) { // Level 7 - 60 Kills
				Utils.setLevel(killer, 7);
				killer.sendMessage("");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You are now " + ChatColor.YELLOW + "Level 7" + ChatColor.GOLD + "!");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You need " + ChatColor.YELLOW + "10" + ChatColor.GOLD + " more kills to get to " + ChatColor.YELLOW + "Level 8" + ChatColor.GOLD + "!"); 
				killer.sendMessage("");
				plugin.chat.setPlayerSuffix(killer, ChatColor.GRAY + " [" + ChatColor.YELLOW + "Level 7" + ChatColor.GRAY + "]");
			}
			if(cs.getInt("Kills") == 70) { // Level 8 - 70 Kills
				Utils.setLevel(killer, 8);
				killer.sendMessage("");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You are now " + ChatColor.YELLOW + "Level 8" + ChatColor.GOLD + "!");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You need " + ChatColor.YELLOW + "10" + ChatColor.GOLD + " more kills to get to " + ChatColor.YELLOW + "Level 9" + ChatColor.GOLD + "!"); 
				killer.sendMessage("");
				plugin.chat.setPlayerSuffix(killer, ChatColor.GRAY + " [" + ChatColor.YELLOW + "Level 8" + ChatColor.GRAY + "]");
			}
			if(cs.getInt("Kills") == 80) { // Level 9 - 80 Kills
				Utils.setLevel(killer, 9);
				killer.sendMessage("");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You are now " + ChatColor.YELLOW + "Level 9" + ChatColor.GOLD + "!");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You need " + ChatColor.YELLOW + "10" + ChatColor.GOLD + " more kills to get to " + ChatColor.YELLOW + "Level 10" + ChatColor.GOLD + "!"); 
				killer.sendMessage("");
				plugin.chat.setPlayerSuffix(killer, ChatColor.GRAY + " [" + ChatColor.YELLOW + "Level 9" + ChatColor.GRAY + "]");
			}
			if(cs.getInt("Kills") == 100) { // Level 10 - 100 Kills
				Utils.setLevel(killer, 10);
				killer.sendMessage("");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You are now " + ChatColor.YELLOW + "Level 10" + ChatColor.GOLD + "!");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You need " + ChatColor.YELLOW + "20" + ChatColor.GOLD + " more kills to get to " + ChatColor.YELLOW + "Level 11" + ChatColor.GOLD + "!"); 
				killer.sendMessage("");
				plugin.chat.setPlayerSuffix(killer, ChatColor.GRAY + " [" + ChatColor.YELLOW + "Level 10" + ChatColor.GRAY + "]");
			}
			if(cs.getInt("Kills") == 125) { // Level 11 - 125 Kills
				Utils.setLevel(killer, 11);
				killer.sendMessage("");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You are now " + ChatColor.YELLOW + "Level 11" + ChatColor.GOLD + "!");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You need " + ChatColor.YELLOW + "25" + ChatColor.GOLD + " more kills to get to " + ChatColor.YELLOW + "Level 12" + ChatColor.GOLD + "!"); 
				killer.sendMessage("");
				plugin.chat.setPlayerSuffix(killer, ChatColor.GRAY + " [" + ChatColor.YELLOW + "Level 11" + ChatColor.GRAY + "]");
			}
			if(cs.getInt("Kills") == 150) { // Level 12 - 150 Kills
				Utils.setLevel(killer, 12);
				killer.sendMessage("");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You are now " + ChatColor.YELLOW + "Level 12" + ChatColor.GOLD + "!");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You need " + ChatColor.YELLOW + "25" + ChatColor.GOLD + " more kills to get to " + ChatColor.YELLOW + "Level 13" + ChatColor.GOLD + "!"); 
				killer.sendMessage("");
				plugin.chat.setPlayerSuffix(killer, ChatColor.GRAY + " [" + ChatColor.YELLOW + "Level 12" + ChatColor.GRAY + "]");
			}
			if(cs.getInt("Kills") == 175) { // Level 13 - 175 Kills
				Utils.setLevel(killer, 13);
				killer.sendMessage("");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You are now " + ChatColor.YELLOW + "Level 13" + ChatColor.GOLD + "!");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You need " + ChatColor.YELLOW + "25" + ChatColor.GOLD + " more kills to get to " + ChatColor.YELLOW + "Level 14" + ChatColor.GOLD + "!"); 
				killer.sendMessage("");
				plugin.chat.setPlayerSuffix(killer, ChatColor.GRAY + " [" + ChatColor.YELLOW + "Level 13" + ChatColor.GRAY + "]");
			}
			if(cs.getInt("Kills") == 200) { // Level 14 - 200 Kills
				Utils.setLevel(killer, 14);
				killer.sendMessage("");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You are now " + ChatColor.YELLOW + "Level 14" + ChatColor.GOLD + "!");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You need " + ChatColor.YELLOW + "50" + ChatColor.GOLD + " more kills to get to " + ChatColor.YELLOW + "Level 15" + ChatColor.GOLD + "!");
				killer.sendMessage("");
				plugin.chat.setPlayerSuffix(killer, ChatColor.GRAY + " [" + ChatColor.YELLOW + "Level 14" + ChatColor.GRAY + "]");
			}
			if(cs.getInt("Kills") == 250) { // Level 15 - 250 Kills
				Utils.setLevel(killer, 15);
				killer.sendMessage("");
				Utils.sendCenteredMessage(killer, ChatColor.GOLD + "You are now " + ChatColor.YELLOW + "Level 15" + ChatColor.GOLD + "!");
				killer.sendMessage("");
				plugin.chat.setPlayerSuffix(killer, ChatColor.GRAY + " [" + ChatColor.YELLOW + "Level 15" + ChatColor.GRAY + "]");
			}	
			
			if(e.getDrops().contains(InventoryClick.skull)) {
				e.getDrops().remove(InventoryClick.skull);
			}
			
			if(e.getDrops().contains(InventoryClick.blazeRod)) {
				e.getDrops().remove(InventoryClick.blazeRod); 
			}
			
			if(e.getDrops().contains(InventoryClick.feather)) {
				e.getDrops().remove(InventoryClick.feather);
			}
			
			if(e.getDrops().contains(InventoryClick.fireCharge)) {
				e.getDrops().remove(InventoryClick.fireCharge);
			}
			
			if(e.getDrops().contains(InventoryClick.lightningStrike)) {
				e.getDrops().remove(InventoryClick.lightningStrike);
			}
		}
		else {
			return;
		}
	}
}
