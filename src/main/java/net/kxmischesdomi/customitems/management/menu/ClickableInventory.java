package net.kxmischesdomi.customitems.management.menu;

import net.kxmischesdomi.customitems.utils.bukkit.event.PlayerInventoryClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.function.Consumer;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ClickableInventory extends SlotMenuPosition {

	private final Inventory inventory;

	public ClickableInventory(Inventory inventory) {
		if (inventory.getHolder() != MenuPosition.HOLDER) throw new IllegalArgumentException("Inventory holder must be MenuPosition#HOLDER");
		this.inventory = inventory;
	}

	public void setItem(@Nonnegative int slot, @Nonnull ItemStack itemStack, @Nonnull Consumer<PlayerInventoryClickEvent> action) {
		inventory.setItem(slot, itemStack);
		setAction(slot, action);
	}

	public void open(@Nonnull Player player) {
		MenuPosition.set(player, this);
		player.openInventory(inventory);
	}

	public Inventory getInventory() {
		return inventory;
	}

}
    