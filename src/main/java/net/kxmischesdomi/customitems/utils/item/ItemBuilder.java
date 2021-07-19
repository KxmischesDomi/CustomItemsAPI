package net.kxmischesdomi.customitems.utils.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ItemBuilder {

	public static ItemStack createItem(@Nonnull Material material, String name) {
		ItemStack itemStack = new ItemStack(material);
		ItemMeta meta = itemStack.getItemMeta();
		if (meta == null) return itemStack;
		meta.setDisplayName(name);
		itemStack.setItemMeta(meta);
		return itemStack;
	}

}
