package net.kxmischesdomi.customitems.api.item;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public abstract class InteractCustomItem extends CustomItem {

	@EventHandler(priority = EventPriority.LOW)
	public final void defaultInteract(@Nonnull PlayerInteractEvent event) {
		if (event.getItem() == null) return;
		if (!isCurrentItem(event.getItem())) return;
		event.setCancelled(true);
		onInteract(event);
	}

	@EventHandler(priority = EventPriority.LOW)
	public final void defaultEntityInteract(@Nonnull PlayerInteractEntityEvent event) {
		if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) return;
		if (!isCurrentItem(event.getPlayer().getInventory().getItemInMainHand())) return;
		event.setCancelled(true);
		onEntityInteract(event);
	}

	public void onEntityInteract(@Nonnull PlayerInteractEntityEvent event) { };
	public void onInteract(@Nonnull PlayerInteractEvent event) { };

}
