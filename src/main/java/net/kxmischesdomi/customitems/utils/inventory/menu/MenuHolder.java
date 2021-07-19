package net.kxmischesdomi.customitems.utils.inventory.menu;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class MenuHolder implements InventoryHolder {

	@Override
	@Nonnull
	public Inventory getInventory() {
		return Bukkit.createInventory(this, 9);
	}

}
    