package net.kxmischesdomi.customitems.bukkit.listener;

import net.kxmischesdomi.customitems.utils.bukkit.event.PlayerInventoryClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CustomEventsListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryClick(@Nonnull InventoryClickEvent event) {
		if (!(event.getWhoClicked() instanceof Player)) return;

		PlayerInventoryClickEvent clickEvent = new PlayerInventoryClickEvent(event);

		Bukkit.getPluginManager().callEvent(clickEvent);
	}
}
    