package net.kxmischesdomi.customitems.item.attribute;

import de.tr7zw.nbtapi.NBTItem;
import net.kxmischesdomi.customitems.utils.bukkit.item.MaterialUtils;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public interface NBTAttribute extends IAttribute {

	@Override
	default void apply(@Nonnull ItemStack itemStack) {
		if (MaterialUtils.isAir(itemStack.getType())) return;
		NBTItem nbtItem = new NBTItem(itemStack, true);
		apply(nbtItem);
	}

	void apply(@Nonnull NBTItem nbtItem);

}
    