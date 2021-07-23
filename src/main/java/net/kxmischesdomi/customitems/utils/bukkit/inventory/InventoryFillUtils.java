package net.kxmischesdomi.customitems.utils.bukkit.inventory;

import net.kxmischesdomi.customitems.utils.bukkit.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class InventoryFillUtils {

	public static void fillInventory(@Nonnull Inventory inventory, @Nonnull Material material) {
		fillInventory(inventory, ItemBuilder.createItem(material, "§0"));
	}

	public static void fillInventory(@Nonnull Inventory inventory, @Nonnull ItemStack itemStack) {
		for (int i = 0; i < inventory.getSize(); i++) {
			inventory.setItem(i, itemStack);
		}
	}

}
    