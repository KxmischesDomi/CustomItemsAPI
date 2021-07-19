package net.kxmischesdomi.customitems.api.tab;

import net.kxmischesdomi.customitems.api.item.ICustomItem;
import net.kxmischesdomi.customitems.utils.inventory.menu.ClickableInventory;
import net.kxmischesdomi.customitems.utils.inventory.generation.ClickableMenuItem;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public interface ICustomTab extends ClickableMenuItem {

	@Nonnull ClickableInventory getInventoryPage(@Nonnegative int page);
	void registerCustomItem(@Nonnull ICustomItem customItem);
	ICustomItem getCustomItem(@Nonnull String key);
	void generateInventories();

}
    