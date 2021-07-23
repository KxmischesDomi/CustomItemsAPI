package net.kxmischesdomi.customitems.utils.bukkit.customitems;

import de.tr7zw.nbtapi.NBTItem;
import net.kxmischesdomi.customitems.CustomItemConstants;
import net.kxmischesdomi.customitems.CustomItems;
import net.kxmischesdomi.customitems.item.ICustomItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CustomItemUtils {

	@Nullable
	public static ICustomItem getCustomItemFromItemStack(@Nonnull ItemStack itemStack) {
		if (itemStack.getType() == Material.AIR) return null;
		String key = getKeyFromItemStack(itemStack);
		if (key == null) return null;
		return CustomItems.getInstance().getCustomItemsManagement().getCustomItem(key);
	}

	@Nullable
	public static String getKeyFromItemStack(@Nonnull ItemStack itemStack) {
		if (itemStack.getType() == Material.AIR) return null;
		NBTItem nbtItem = new NBTItem(itemStack);
		return nbtItem.getString(CustomItemConstants.KEY);
	}

	public static boolean itemEquals(@Nullable ItemStack itemStack1, @Nullable ItemStack itemStack2) {
		if (itemStack1 == itemStack2) return false;
		if (itemStack1 == null || itemStack2 == null) return false;
		String keyFromItemStack1 = getKeyFromItemStack(itemStack1);
		String keyFromItemStack2 = getKeyFromItemStack(itemStack2);
		if (keyFromItemStack1 == null && keyFromItemStack2 == null && itemStack1.isSimilar(itemStack2)) return true;
		return keyFromItemStack1 != null && keyFromItemStack1.equals(keyFromItemStack2);
	}

}
    