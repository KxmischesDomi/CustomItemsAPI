package net.kxmischesdomi.customitems.item.attribute;

import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * Represents an external change of an item
 *
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public interface IAttribute {

	void apply(@Nonnull ItemStack itemStack);

}
