package net.kxmischesdomi.customitems.utils.bukkit.inventory.generation;

import net.kxmischesdomi.customitems.item.ICustomItem;
import net.kxmischesdomi.customitems.utils.bukkit.inventory.InventoryFillUtils;
import net.kxmischesdomi.customitems.management.menu.ClickableInventory;
import net.kxmischesdomi.customitems.management.menu.CustomItemRecipeMenu;
import net.kxmischesdomi.customitems.management.menu.MenuPosition;
import net.kxmischesdomi.customitems.utils.bukkit.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnegative;
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

			int currentSlot = 10;
			for (int i = 0; !items.isEmpty() && i < maxItemsPerPage; i++) {
				ClickableMenuItem item = items.remove(0);

				currentSlot = getNextMiddleSlot(currentSlot);
				int slot = currentSlot;

				inventory.setItem(slot, item.getDisplayItem());

				clickableInventory.setAction(slot, item::onMenuClick);

				currentSlot++;
			}

			inventories.add(clickableInventory);
		}

		return inventories;
	}

	private static int getNextMiddleSlot(@Nonnegative int currentSlot) {
		if (currentSlot >= 53) return currentSlot;
		if (isSideSlot(currentSlot)) return getNextMiddleSlot(currentSlot + 1);
		return currentSlot;
	}

	/**
	 * @param slot the slot which should be checked
	 * @return if the slot is on the side of the inventory
	 */
	private static boolean isSideSlot(@Nonnegative int slot) {
		return slot % 9 == 0 || slot % 9 == 8;
	}

	public static ClickableInventory generateItemInfoInventory(@Nonnull ICustomItem customItem) {
		Inventory inventory = Bukkit.createInventory(MenuPosition.HOLDER, 3 * 9, customItem.getDisplayName());
		ClickableInventory clickableInventory = new ClickableInventory(inventory);

		InventoryFillUtils.fillInventory(inventory, Material.GRAY_STAINED_GLASS_PANE);
		inventory.setItem(10, customItem.getDisplayItem());
		clickableInventory.setItem(13, ItemBuilder.createItem(Material.FISHING_ROD, "§6Get Item"), event -> {
			ItemStack itemStack = customItem.getItemStack();
			if (event.isShiftClick()) itemStack.setAmount(itemStack.getType().getMaxStackSize());
			event.getPlayer().getInventory().addItem(itemStack);
		});
		clickableInventory.setItem(14, ItemBuilder.createItem(Material.CRAFTING_TABLE, "§6Recipes"), event -> new CustomItemRecipeMenu(customItem.getDisplayName()).open(event.getPlayer(), customItem.getItemStack()));

		return clickableInventory;
	}

}
