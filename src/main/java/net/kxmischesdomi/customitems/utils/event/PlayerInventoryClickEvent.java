package net.kxmischesdomi.customitems.utils.event;

import net.kxmischesdomi.customitems.utils.event.clone.CloneInventoryClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Called when a player clicks in an inventory.
 *
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class PlayerInventoryClickEvent extends CloneInventoryClickEvent {

	private static final HandlerList HANDLERS_LIST = new HandlerList();

	private final Player player;

	public PlayerInventoryClickEvent(InventoryClickEvent event) {
		super(event);
		player = ((Player) event.getWhoClicked());
	}

	public Player getPlayer() {
		return player;
	}

	public static HandlerList getHandlerList() {
		return HANDLERS_LIST;
	}

	@Override
	public HandlerList getHandlers() {
		return getHandlerList();
	}

}