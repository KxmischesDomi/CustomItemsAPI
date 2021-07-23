package net.kxmischesdomi.customitems.utils.bukkit;

import org.bukkit.ChatColor;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ChatUtils {

	public static String getWithoutColorCodes(@Nonnull String s) {
		for (ChatColor value : ChatColor.values()) {
			s = s.replace("§" + value.getChar(), "");
		}
		return s;
	}

}
    