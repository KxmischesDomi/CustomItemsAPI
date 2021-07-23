package net.kxmischesdomi.customitems.item.attribute;

import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ResetVanillaAttribute implements IAttribute {

	@Override
	public void apply(@Nonnull ItemStack itemStack) {
		ItemMeta meta = itemStack.getItemMeta();
		if (meta == null) return;
		if (meta.getAttributeModifiers() == null) return;
		for (Attribute attribute : meta.getAttributeModifiers().keys()) {
			meta.removeAttributeModifier(attribute);
		}
		itemStack.setItemMeta(meta);
	}

}
