package me.athena222.kitpvp.listeners;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.athena222.kitpvp.Main;
import me.athena222.kitpvp.util.Utils;

public class InventoryClick implements Listener {

	private Main plugin;
	public static ItemStack skull, lightningStrike, fireCharge, blazeRod, feather;
	
	public InventoryClick(Main instance) {
		plugin = instance;
	}
 
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();

		if (e.getCurrentItem().getItemMeta() == null) return;
		if (e.getCurrentItem() == null) return;
		if (player.getGameMode() == GameMode.CREATIVE) return;
		
		if (e.getInventory().getName().equals(plugin.selectClass.getName())) {
			
			skull = new ItemStack(Material.SKULL_ITEM);
			ItemMeta skullMeta = skull.getItemMeta();
			skullMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Suicide");
			ArrayList<String> skullLore = new ArrayList<String>();
			skullLore.add(ChatColor.GRAY + "Useful if you're stuck.");
			skullMeta.setLore(skullLore);
			skull.setItemMeta(skullMeta);
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Swordsman Class")) {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(ChatColor.GRAY + "You selected the " + ChatColor.AQUA + "" + ChatColor.BOLD + "Swordsman Class");
				Utils.setKit(player, "Swordsman");
				player.getInventory().clear();			
				ItemStack[] stack = { 
						new ItemStack(Material.LEATHER_BOOTS),
						new ItemStack(Material.LEATHER_LEGGINGS),
						new ItemStack(Material.LEATHER_CHESTPLATE),
						new ItemStack(Material.LEATHER_HELMET), };	
				player.getInventory().setArmorContents(stack);			
				player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
				
				lightningStrike = new ItemStack(Material.BLAZE_POWDER);
				ItemMeta lightningMeta = lightningStrike.getItemMeta();
				lightningMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Lightning Strike Ability");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(ChatColor.GRAY + "Right-click to strike!");
				lightningMeta.setLore(lore);
				lightningStrike.setItemMeta(lightningMeta);			
				//blaze powder (slot 2)  Right click Lightning strike ability: Cooldown 10 seconds. Lure: Right-click to strike!
		        //   if you right click while having a cooldown in the chat will appear in red: You  
		        //   have to wait .... seconds!
				
				player.getInventory().addItem(lightningStrike);
				player.getInventory().setItem(17, skull);			
			}
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "" + ChatColor.BOLD + "Archer Class")) {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(ChatColor.GRAY + "You selected the " + ChatColor.GREEN + "" + ChatColor.BOLD + "Archer Class");
				Utils.setKit(player, "Archer");
				player.getInventory().clear();
				ItemStack[] stack = { 
						new ItemStack(Material.LEATHER_BOOTS),
						new ItemStack(Material.LEATHER_LEGGINGS),
						new ItemStack(Material.LEATHER_CHESTPLATE),
						new ItemStack(Material.LEATHER_HELMET), };	
				player.getInventory().setArmorContents(stack);		
				player.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
				player.getInventory().addItem(new ItemStack(Material.BOW));
				
				fireCharge = new ItemStack(Material.FIREBALL);
				ItemMeta chargeMeta = fireCharge.getItemMeta();
				chargeMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Fire Charge");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(ChatColor.GRAY + "Right-click to fire!");
				chargeMeta.setLore(lore);
				fireCharge.setItemMeta(chargeMeta);
				//Fire charge: right click will send a fireball with explosion (no block damage) cooldown 20 seconds. Lure: Right-click to fire! (slot 3)
		        //   if you right click while having a cooldown in the chat will appear in red: You  
		        //  have to wait .... seconds!
				
				player.getInventory().addItem(fireCharge);
				player.getInventory().addItem(new ItemStack(Material.ARROW, 32));
			}

			if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Mage Class")) {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(ChatColor.GRAY + "You selected the " + ChatColor.GOLD + "" + ChatColor.BOLD + "Mage Class");
				Utils.setKit(player, "Mage");
				player.getInventory().clear();
				ItemStack[] stack = { 
						new ItemStack(Material.LEATHER_BOOTS),
						new ItemStack(Material.LEATHER_LEGGINGS),
						new ItemStack(Material.LEATHER_CHESTPLATE),
						new ItemStack(Material.LEATHER_HELMET), };	
				player.getInventory().setArmorContents(stack);	
				player.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
							
				blazeRod = new ItemStack(Material.BLAZE_ROD);
				ItemMeta rodMeta = blazeRod.getItemMeta();
				rodMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Blaze Rod");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(ChatColor.GRAY + "Right-click to fire!");
				rodMeta.setLore(lore);
				blazeRod.setItemMeta(rodMeta);
				//Blaze rod (slot 2) Right click will send fire particles at the enemy. if the fire particles hit the enemy, the enemy will start burning for 10 seconds
		        //   Lure: Right-click to fire! 
				
				feather = new ItemStack(Material.FEATHER);
				ItemMeta featherMeta = feather.getItemMeta();
				featherMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Feather");
				ArrayList<String> featherLore = new ArrayList<String>();
				featherLore.add(ChatColor.GRAY + "Right-click to fly!");
				featherMeta.setLore(featherLore);
				feather.setItemMeta(featherMeta);
				//Feather (slot 3) Right click will launch you up for 3 seconds with a smoke particle. cooldown 10 seconds.  Lure: Right-click to fly! 
		        //   if you right click while having a cooldown in the chat will appear in red: You     
		        //   have to wait .... seconds!
				
				player.getInventory().addItem(blazeRod);
				player.getInventory().addItem(feather);				
			}
			
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Exit")) {
				e.setCancelled(true);
				player.closeInventory();
			}
		
			else if ((e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) && (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLACK + ""))) {
				e.setCancelled(true);
			}
		}

		if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Kit Selector") || (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Upgrader"))) {
			e.setCancelled(true);
		}
		
		if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Suicide")) {
			e.setCancelled(true);
			player.performCommand("suicide");
		}
	}
}