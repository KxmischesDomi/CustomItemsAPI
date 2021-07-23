package net.kxmischesdomi.customitems.item.attribute;

import de.tr7zw.nbtapi.NBTItem;
import net.kxmischesdomi.customitems.CustomItemConstants;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public final class KeyAttribute implements NBTAttribute {

	private final Supplier<String> stringSupplier;

	public KeyAttribute(@Nonnull String key) {
		this(() -> key);
	}

	public KeyAttribute(@Nonnull Supplier<String> stringSupplier) {
		this.stringSupplier = stringSupplier;
	}

	@Override
	public void apply(@Nonnull NBTItem nbtItem) {
		nbtItem.setString(CustomItemConstants.KEY, stringSupplier.get());
	}

}
