package net.kxmischesdomi.customitems.utils.bukkit.inventory.generation;

import net.kxmischesdomi.customitems.utils.bukkit.event.PlayerInventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public interface ClickableMenuItem {

	@Nonnull ItemStack getDisplayItem();
	void onMenuClick(PlayerInventoryClickEvent event);

}
