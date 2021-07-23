package net.kxmischesdomi.customitems.tab;

import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CustomTab extends AbstractCustomTab {

	private final ItemStack displayItem;

	public CustomTab(ItemStack displayItem) {
		this.displayItem = displayItem;
	}

	@Nonnull
	@Override
	public ItemStack getDisplayItem() {
		return displayItem;
	}
}
    