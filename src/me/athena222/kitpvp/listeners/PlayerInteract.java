package me.athena222.kitpvp.listeners;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import me.athena222.kitpvp.Main;

public class PlayerInteract implements Listener {

	private Main plugin;
	public HashMap<String, Long> cooldown = new HashMap<String, Long>();
	
	public PlayerInteract(Main instance) {
		plugin = instance;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) { // add cooldowns
	
		Player player = (Player) e.getPlayer();
		
		if(player.getGameMode() == GameMode.CREATIVE) return;
		
		if((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {		
			if(e.getItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Kit Selector") && (e.getItem().getType().equals(Material.BLAZE_ROD))) { 
				player.openInventory(plugin.selectClass);
			}
			
			if(e.getItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Upgrader") && (e.getItem().getType().equals(Material.EMERALD))) {
				player.openInventory(plugin.upgrader);
			}
			
			/*
			if(cooldown.containsKey(player.getName())) {
			    long secondsLeft = ((cooldown.get(player.getName()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
			    if(secondsLeft > 0) {
			    	player.sendMessage(ChatColor.RED + "You cant use that command for another " + secondsLeft + " seconds!");
			    	player.sendMessage(ChatColor.GRAY + "Please note that cooldowns are " + ChatColor.UNDERLINE + "3 days");	
			    	}
			    else if (secondsLeft == 0) {
			  
			    }
			} */
			
			// else {
			
			if(e.getItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Lightning Strike Ability") && (e.getItem().getType().equals(Material.BLAZE_POWDER))) { // lightning strike			
				Location origin = player.getEyeLocation();
				Vector direction = player.getLocation().getDirection(); 
				int range = 7; 
				
				for (int i = 1; i <= range; i++) { 	
					origin.add(direction.getX() * i, direction.getY() * i, direction.getY() * i); 
					
					for(Entity entity : player.getNearbyEntities(origin.getX(), origin.getY(), origin.getZ())) { 
						if(entity instanceof Player) {
							entity.getWorld().strikeLightning(entity.getLocation()); 
						}	
					} 	
					origin.subtract(direction.getX() * i, direction.getY() * i, direction.getZ() * i); 
				}
			}
			
			if(e.getItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Fire Charge") && (e.getItem().getType().equals(Material.FIREBALL))) { // fire charge
				e.setCancelled(true); // no damage if its your fireball? Fireball hit event? see if the fireball is the player's name?
				Fireball fireball = player.getWorld().spawn(player.getEyeLocation().add(0, 1, 0), Fireball.class);
				fireball.setIsIncendiary(false);
			//	cooldown.put(player.getUniqueId().toString(), 60L);
			}
			
			if(e.getItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Blaze Rod") && (e.getItem().getType().equals(Material.BLAZE_ROD))) {
				// blaze rod	 
			}
			
			if(e.getItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Feather") && (e.getItem().getType().equals(Material.FEATHER))) {
				// feather
				// https://bukkit.org/threads/launch-players-into-the-air.60946/#post-974467				
			}
		}
	}
}