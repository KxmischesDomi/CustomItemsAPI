package net.kxmischesdomi.customitems.test.items.game;

import net.kxmischesdomi.customitems.item.type.RightInteractableCustomItem;
import net.kxmischesdomi.customitems.management.menu.ClickableInventory;
import net.kxmischesdomi.customitems.management.menu.MenuPosition;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public abstract class InventoryGameConsoleItem extends RightInteractableCustomItem {

	@Override
	public void onRightClick(@Nonnull PlayerInteractEvent event) {
		createInventory().open(event.getPlayer());
	}

	public ClickableInventory createInventory() {
		Inventory inventory = Bukkit.createInventory(MenuPosition.HOLDER, getInventorySize(), getInventoryTitle());
		ClickableInventory clickableInventory = new ClickableInventory(inventory);
		fillInventory(clickableInventory);
		return clickableInventory;
	}

	public abstract int getInventorySize();
	public abstract String getInventoryTitle();
	public abstract void fillInventory(@Nonnull ClickableInventory clickableInventory);

}
    