package me.athena222.kitpvp.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChange implements Listener {

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		if(e.getEntity() instanceof Player) {
			e.setFoodLevel(20);
			e.setCancelled(true);
		}
		else {
			return;
		}
	}	
}