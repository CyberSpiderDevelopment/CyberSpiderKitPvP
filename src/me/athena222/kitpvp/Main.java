package me.athena222.kitpvp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.athena222.kitpvp.commands.CommandAdmin;
import me.athena222.kitpvp.listeners.FoodLevelChange;
import me.athena222.kitpvp.listeners.InventoryClick;
import me.athena222.kitpvp.listeners.PlayerDeath;
import me.athena222.kitpvp.listeners.PlayerInteract;
import me.athena222.kitpvp.listeners.PlayerJoin;
import me.athena222.kitpvp.util.Utils;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

public class Main extends JavaPlugin {
	
	public Inventory selectClass, upgrader;
	public ItemStack swordsman, archer, mage, barrier, pane;
	
	public Permission perms = null;
    public Chat chat = null;
	public Logger logger = getLogger();
	public File users = new File(getDataFolder(), "users.yml");
	public FileConfiguration config = getConfig();
	public FileConfiguration usersConfig;
	
	public void onEnable() {
		saveDefaultConfig();
		setupUserConfig();
		saveData();
		registerCommands();
		registerListeners();
		setupSelectClassInv();
        setupPermissions();
		setupChat();
		
		if(!getServer().getPluginManager().isPluginEnabled("FeatherBoard")) {
			logger.warning("Couldn't find FeatherBoard! Placeholders for this plugin will no longer work.");
		}
		else if(getServer().getPluginManager().isPluginEnabled("FeatherBoard")) {
			logger.info("Successfully found FeatherBoard! Hooking into it to add placeholders.");
			
			be.maximvdw.featherboard.api.PlaceholderAPI.registerOfflinePlaceholder("kitpvp_kit", true, 
					new be.maximvdw.featherboard.api.PlaceholderAPI.PlaceholderRequestEventHandler() {
	                    @Override
	                    public String onPlaceholderRequest(be.maximvdw.featherboard.api.PlaceholderAPI.PlaceholderRequestEvent e) {                 	
	                        String kit = usersConfig.getConfigurationSection("users" + "." + e.getPlayer().getUniqueId().toString()).getString("Kit");
	                        if(kit.equals("None")) {
	                        	return ChatColor.RED + "No kit";
	                        }
	                        return ChatColor.LIGHT_PURPLE + kit;                  
	                    }
	           		});
			
			be.maximvdw.featherboard.api.PlaceholderAPI.registerOfflinePlaceholder("kitpvp_coins", true, 
					new be.maximvdw.featherboard.api.PlaceholderAPI.PlaceholderRequestEventHandler() {
	                    @Override
	                    public String onPlaceholderRequest(be.maximvdw.featherboard.api.PlaceholderAPI.PlaceholderRequestEvent e) {                    	
	                        String coins = ChatColor.GOLD + "" + usersConfig.getConfigurationSection("users" + "." + e.getPlayer().getUniqueId().toString()).getInt("Coins");
	                        return coins;                  
	                    }
	           		});      
		}
		
		Utils.plugin = this;
		
		pane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)7);
		ItemMeta paneMeta = pane.getItemMeta();
		paneMeta.setDisplayName(ChatColor.BLACK + "");
		pane.setItemMeta(paneMeta);
		
		selectClass = getServer().createInventory(null, 45, "Select Class");
		selectClass.setItem(20, swordsman);
		selectClass.setItem(22, archer);
		selectClass.setItem(24, mage);
		selectClass.setItem(44, barrier);
		
		upgrader = getServer().createInventory(null, 45, "Upgrader");
		
		int slot;
		while ((slot = selectClass.firstEmpty()) != -1) 
			selectClass.setItem(slot, pane);	
	}
	
	public void registerCommands() {
		getCommand("admin").setExecutor(new CommandAdmin(this));
	}
	
	public void registerListeners() {
		getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
		getServer().getPluginManager().registerEvents(new PlayerInteract(this), this);
		getServer().getPluginManager().registerEvents(new InventoryClick(this), this);
		getServer().getPluginManager().registerEvents(new FoodLevelChange(), this);
		getServer().getPluginManager().registerEvents(new PlayerDeath(this), this);
	}
	
	public void setupSelectClassInv() {		
		swordsman = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta swordsMeta = swordsman.getItemMeta();
		swordsMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Swordsman Class");
		ArrayList<String> swordsLore = new ArrayList<String>();
		swordsLore.add(ChatColor.GRAY + "Click to the Swordsman Class.");
		swordsMeta.setLore(swordsLore);
		swordsman.setItemMeta(swordsMeta);
				 
		archer = new ItemStack(Material.BOW);
		ItemMeta archerMeta = archer.getItemMeta();
		archerMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Archer Class");
		ArrayList<String> archerLore = new ArrayList<String>();
		archerLore.add(ChatColor.GRAY + "Click to the Archer Class.");
		archerMeta.setLore(archerLore);
		archer.setItemMeta(archerMeta);
		
		mage = new ItemStack(Material.BLAZE_ROD);
		ItemMeta mageMeta = mage.getItemMeta();
		mageMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Mage Class");
		ArrayList<String> mageLore = new ArrayList<String>();
		mageLore.add(ChatColor.GRAY + "Click to the Mage Class.");
		mageMeta.setLore(mageLore);
		mage.setItemMeta(mageMeta);
		
		barrier = new ItemStack(Material.BARRIER);
		ItemMeta barrierMeta = barrier.getItemMeta();
		barrierMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Exit");
		ArrayList<String> barrierLore = new ArrayList<String>();
		barrierLore.add(ChatColor.GRAY + "Close this GUI.");
		barrierMeta.setLore(barrierLore);
		barrier.setItemMeta(barrierMeta);
	}
	
	public void setupUserConfig() {
		if (!users.exists()) {
			try {
				users.createNewFile();
				this.saveResource("users.yml", true);
			} catch (IOException e) {
				logger.severe("Could not create users.yml!");
			}
		}
		usersConfig = YamlConfiguration.loadConfiguration(users);
	}

	public void saveData() {
		try {
			usersConfig.save(users);
		} catch (IOException e) {
			logger.severe("Could not save users.yml!");
		}
	}

	public void reloadData() {
		usersConfig = YamlConfiguration.loadConfiguration(users);
	}
	
    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }
    
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
}