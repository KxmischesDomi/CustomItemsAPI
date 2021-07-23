package net.kxmischesdomi.customitems.item.attribute;

import de.tr7zw.nbtapi.NBTItem;
import net.kxmischesdomi.customitems.CustomItemConstants;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.function.Supplier;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public final class CustomModelDataAttribute implements NBTAttribute {

	private final Supplier<Integer> integerFunction;

	public CustomModelDataAttribute(@Nonnegative int customModelData) {
		this(() -> customModelData);
	}

	public CustomModelDataAttribute(Supplier<Integer> integerFunction) {
		this.integerFunction = integerFunction;
	}

	@Override
	public void apply(@Nonnull NBTItem nbtItem) {
		int integer = integerFunction.get();
		if (integer == 0) return;
		nbtItem.setInteger(CustomItemConstants.CUSTOM_MODEL_DATA, integer);
	}

}
    