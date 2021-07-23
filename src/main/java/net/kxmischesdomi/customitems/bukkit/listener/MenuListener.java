package net.kxmischesdomi.customitems.bukkit.listener;

import net.kxmischesdomi.customitems.utils.bukkit.event.PlayerInventoryClickEvent;
import net.kxmischesdomi.customitems.management.menu.MenuPosition;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class MenuListener implements Listener {

	@EventHandler(priority = EventPriority.LOW)
	public void onInventoryClick(PlayerInventoryClickEvent event) {
		if (event.getClickedInventory() == null) return;
		if (event.getClickedInventory().getHolder() != MenuPosition.HOLDER) return;
		event.setCancelled(true);

		MenuPosition position = MenuPosition.get(event.getPlayer());
		if (position != null) {
			position.handleClick(event);
		}

	}

}
    