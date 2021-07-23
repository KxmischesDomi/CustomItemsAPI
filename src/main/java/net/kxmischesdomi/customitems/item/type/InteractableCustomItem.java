package net.kxmischesdomi.customitems.item.type;

import net.kxmischesdomi.customitems.item.AbstractCustomItem;
import net.kxmischesdomi.customitems.item.attribute.IAttribute;
import org.bukkit.GameMode;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public abstract class InteractableCustomItem extends AbstractCustomItem {

	public InteractableCustomItem(IAttribute... attributes) {
		super(attributes);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void defaultInteract(@Nonnull PlayerInteractEvent event) {
		if (event.getPlayer().getGameMode() == GameMode.SPECTATOR) return;
		if (event.getClickedBlock() != null && event.getClickedBlock().getType().isInteractable()) return;
		if (event.getHand() != EquipmentSlot.OFF_HAND && event.getHand() != EquipmentSlot.HAND) return;
		if (!isCurrentItem(event.getItem())) return;
		event.setUseItemInHand(Result.DENY);
		onPlayerInteract(event);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void defaultInteractEntity(@Nonnull PlayerInteractEntityEvent event) {
		if (event.getPlayer().getGameMode() == GameMode.SPECTATOR) return;
		if (event.getHand() != EquipmentSlot.OFF_HAND && event.getHand() != EquipmentSlot.HAND) return;
		if (!isCurrentItem(event.getPlayer().getInventory().getItemInMainHand())) return;
		event.setCancelled(true);
		onPlayerInteractEntity(event);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void defaultDispense(@Nonnull BlockDispenseEvent event) {
		if (!isCurrentItem(event.getItem())) return;
		event.setCancelled(true);
		if (onDispenserInteract(event)) {
			event.setCancelled(false);
		}

	}

	public void onPlayerInteractEntity(@Nonnull PlayerInteractEntityEvent event) { };
	public void onPlayerInteract(@Nonnull PlayerInteractEvent event) { };

	/**
	 * @return if item should be dropped
	 */
	public boolean onDispenserInteract(@Nonnull BlockDispenseEvent event) { return true; };

}
