package net.kxmischesdomi.customitems.utils.inventory.generation;

import net.kxmischesdomi.customitems.api.item.ICustomItem;
import net.kxmischesdomi.customitems.utils.inventory.InventoryFillUtils;
import net.kxmischesdomi.customitems.utils.inventory.menu.ClickableInventory;
import net.kxmischesdomi.customitems.utils.inventory.menu.CustomItemRecipeMenu;
import net.kxmischesdomi.customitems.utils.inventory.menu.MenuPosition;
import net.kxmischesdomi.customitems.utils.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class InventoryGenerator {

	private InventoryGenerator() {
	}

	public static List<ClickableInventory> generateListInventory(@Nonnull String title, Material background, ClickableMenuItem... clickableMenuItems) {
		return generateListInventory(title, ItemBuilder.createItem(background, "§0"), clickableMenuItems);
	}

	public static List<ClickableInventory> generateListInventory(@Nonnull String title, ItemStack background, ClickableMenuItem... clickableMenuItems) {
		final int maxItemsPerPage = 28;

		List<ClickableInventory> inventories = new ArrayList<>();

		// New list to remove already added items
		List<ClickableMenuItem> items = new ArrayList<>(Arrays.asList(clickableMenuItems));

		// divides the number of items by the number of items per page and rounds it up to get the number of pages needed.
		int pages = (int) Math.ceil((float) clickableMenuItems.length / maxItemsPerPage);
		if (pages == 0) pages = 1;

		for (int page = 0; page < pages; page++) {
			Inventory inventory = Bukkit.createInventory(MenuPosition.HOLDER, 6 * 9, title);
			InventoryFillUtils.fillInventory(inventory, background);
			ClickableInventory clickableInventory = new ClickableInventory(inventory);

			for (int i = 0; !items.isEmpty() && i < maxItemsPerPage; i++) {
				ClickableMenuItem item = items.remove(0);

				int slot = i + 10;
				inventory.setItem(slot, item.getDisplayItem());

				clickableInventory.setAction(slot, item::onMenuClick);
			}

			inventories.add(clickableInventory);
		}

		return inventories;
	}

	public static ClickableInventory generateItemInfoInventory(@Nonnull ICustomItem customItem) {
		Inventory inventory = Bukkit.createInventory(MenuPosition.HOLDER, 3 * 9, customItem.getDisplayName());
		ClickableInventory clickableInventory = new ClickableInventory(inventory);

		InventoryFillUtils.fillInventory(inventory, Material.GRAY_STAINED_GLASS_PANE);
		inventory.setItem(10, customItem.getDisplayItem());
		clickableInventory.setItem(14, ItemBuilder.createItem(Material.CRAFTING_TABLE, "§6Recipes"), event -> new CustomItemRecipeMenu(customItem.getDisplayName()).open(event.getPlayer(), customItem.getItemStack()));
		clickableInventory.setItem(13, ItemBuilder.createItem(Material.FISHING_ROD, "§6Get Item"), event -> event.getPlayer().getInventory().addItem(customItem.getItemStack()));

		return clickableInventory;
	}

}
