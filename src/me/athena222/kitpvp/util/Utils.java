package me.athena222.kitpvp.util;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.athena222.kitpvp.Main;

public class Utils {

	public static Main plugin;
	private final static int CENTER_PX = 154;

	public static void createPlayerDataSection(Player player) {
		plugin.usersConfig.createSection("users" + "." + player.getUniqueId().toString());
		ConfigurationSection cs = plugin.usersConfig.getConfigurationSection("users" + "." + player.getUniqueId().toString());
		cs.set("Kit", "None");
		cs.set("Coins", 0);
		cs.set("Kills", 0);
		cs.set("Level", 0);
		plugin.saveData();
	}

	public static void setKit(Player player, String kit) {
		ConfigurationSection cs = plugin.usersConfig.getConfigurationSection("users" + "." + player.getUniqueId().toString());
		cs.set("Kit", kit);
		plugin.saveData();
	}

	public static void setLevel(Player player, int level) {
		ConfigurationSection cs = plugin.usersConfig.getConfigurationSection("users" + "." + player.getUniqueId().toString());
		cs.set("Level", level);
		plugin.saveData();
	}

	public static void sendCenteredMessage(Player player, String message) {
		if (message == null || message.equals(""))
			player.sendMessage("");
		message = ChatColor.translateAlternateColorCodes('&', message);

		int messagePxSize = 0;
		boolean previousCode = false;
		boolean isBold = false;

		for (char c : message.toCharArray()) {
			if (c == '§') {
				previousCode = true;
				continue;
			} else if (previousCode == true) {
				previousCode = false;
				if (c == 'l' || c == 'L') {
					isBold = true;
					continue;
				} else
					isBold = false;
			} else {
				DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
				messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
				messagePxSize++;
			}
		}

		int halvedMessageSize = messagePxSize / 2;
		int toCompensate = CENTER_PX - halvedMessageSize;
		int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
		int compensated = 0;
		StringBuilder sb = new StringBuilder();
		while (compensated < toCompensate) {
			sb.append(" ");
			compensated += spaceLength;
		}
		player.sendMessage(sb.toString() + message);
	}
}