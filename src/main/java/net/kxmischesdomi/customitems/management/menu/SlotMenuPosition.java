package net.kxmischesdomi.customitems.management.menu;

import net.kxmischesdomi.customitems.utils.bukkit.event.PlayerInventoryClickEvent;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class SlotMenuPosition implements MenuPosition {

	private final Map<Integer, Consumer<PlayerInventoryClickEvent>> slotActions = new LinkedHashMap<>();

	@Override
	public void handleClick(PlayerInventoryClickEvent event) {
		Consumer<PlayerInventoryClickEvent> action = slotActions.getOrDefault(event.getSlot(), slotActions.get(-1));
		if (action == null) return;
		action.accept(event);
	}

	public void setAction(@Nonnegative int slot, @Nonnull Consumer<PlayerInventoryClickEvent> action) {
		slotActions.put(slot, action);
	}

	/**
	 * Sets the action which is executed when a player clicks on a non defined slot
	 */
	public void setOtherSlotAction(@Nonnull Consumer<PlayerInventoryClickEvent> action) {
		slotActions.put(-1, action);
	}

	public Map<Integer, Consumer<PlayerInventoryClickEvent>> getSlotActions() {
		return slotActions;
	}

}
    