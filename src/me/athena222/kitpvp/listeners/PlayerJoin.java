package me.athena222.kitpvp.listeners;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.athena222.kitpvp.Main;
import me.athena222.kitpvp.util.Utils;

public class PlayerJoin implements Listener {

	private Main plugin;
	public static ItemStack kitSelector, upgrader;
	
	public PlayerJoin(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
	
		Player player = (Player) e.getPlayer();
		ConfigurationSection cs = plugin.usersConfig.getConfigurationSection("users" + "." + player.getUniqueId().toString());
		
		kitSelector = new ItemStack(Material.BLAZE_ROD);
		ItemMeta kitMeta = kitSelector.getItemMeta();
		kitMeta.setDisplayName(ChatColor.RED + "Kit Selector");
		ArrayList<String> selectorLore = new ArrayList<String>();
		selectorLore.add(ChatColor.GREEN + "Right-click to choose a kit!");
		kitMeta.setLore(selectorLore);
		kitSelector.setItemMeta(kitMeta);
		
		upgrader = new ItemStack(Material.EMERALD);
		ItemMeta upgraderMeta = upgrader.getItemMeta();
		upgraderMeta.setDisplayName(ChatColor.DARK_GREEN + "Upgrader");
		ArrayList<String> upgraderLore = new ArrayList<String>();
		upgraderLore.add(ChatColor.GREEN + "Right-click to upgrade.");
		upgraderMeta.setLore(upgraderLore);
		upgrader.setItemMeta(upgraderMeta);

		Utils.createPlayerDataSection(player);
		
		player.getInventory().addItem(kitSelector);
		player.getInventory().addItem(upgrader);
		
		if(cs.getString("Kit").equals("Swordsman")) {
			// giveKit command?
		}
		else if(cs.getString("Kit").equals("Archer")) {		
			
		}
		else if(cs.getString("Kit").equals("Mage")) {
			
		}
		else {
			player.getInventory().addItem(kitSelector);
			player.getInventory().addItem(upgrader);
		}
		
		if(!player.hasPlayedBefore()) {		
			if(!player.getInventory().contains(kitSelector)) {
				player.getInventory().setItem(0, kitSelector);		
			}
			if(!player.getInventory().contains(upgrader)) {
				player.getInventory().setItem(4, upgrader);
			}
		}		
	}
}