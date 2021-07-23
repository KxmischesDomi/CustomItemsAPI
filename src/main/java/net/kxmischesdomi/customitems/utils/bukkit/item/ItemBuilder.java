package net.kxmischesdomi.customitems.utils.bukkit.item;

import net.kxmischesdomi.customitems.item.attribute.IAttribute;
import net.kxmischesdomi.customitems.item.attribute.NBTAttribute;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ItemBuilder {

	public static ItemStack createItem(@Nonnull Material material, @Nonnull String name) {
		ItemStack itemStack = new ItemStack(material);
		ItemMeta meta = itemStack.getItemMeta();
		if (meta == null) return itemStack;
		meta.setDisplayName(name.startsWith("§") ? name : "§f" + name);
		itemStack.setItemMeta(meta);
		return itemStack;
	}

	public static ItemStack createItem(@Nonnull Material material, @Nonnull String name, IAttribute... attributes) {
		return applyAttributes(createItem(material, name), attributes);
	}

	public static ItemStack applyAttributes(@Nonnull ItemStack itemStack, IAttribute... attributes) {
		for (IAttribute attribute : attributes) {
			attribute.apply(itemStack);
		}
		return itemStack;
	}


}
