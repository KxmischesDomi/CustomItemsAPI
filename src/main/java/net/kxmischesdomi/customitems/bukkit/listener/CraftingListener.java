package net.kxmischesdomi.customitems.bukkit.listener;

import net.kxmischesdomi.customitems.item.ICustomItem;
import net.kxmischesdomi.customitems.utils.bukkit.customitems.CustomItemUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CraftingListener implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public void onCraft(@Nonnull PrepareItemCraftEvent event) {
		if (event.getRecipe() == null) return;
		ICustomItem customItem = CustomItemUtils.getCustomItemFromItemStack(event.getRecipe().getResult());
		if (customItem != null && customItem.getRecipe() != null) return;

		for (ItemStack itemStack : event.getInventory().getContents()) {
			customItem = CustomItemUtils.getCustomItemFromItemStack(itemStack);
			if (customItem != null && !customItem.allowVanillaCrafting()) {
				event.getInventory().setResult(null);
				return;
			}

		}

	}

}
    