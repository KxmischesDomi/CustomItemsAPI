package net.kxmischesdomi.customitems.utils.bukkit.item;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.inventory.ItemStack;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ItemUtils {

	public static boolean isAir(@Nullable ItemStack itemStack) {
		return itemStack == null || MaterialUtils.isAir(itemStack.getType());
	}

}
    