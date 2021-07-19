package net.kxmischesdomi.customitems.api.management;

import net.kxmischesdomi.customitems.CustomItems;
import net.kxmischesdomi.customitems.api.item.ICustomItem;
import net.kxmischesdomi.customitems.api.tab.ICustomTab;
import net.kxmischesdomi.customitems.utils.inventory.generation.ClickableMenuItem;
import net.kxmischesdomi.customitems.utils.inventory.generation.InventoryGenerator;
import net.kxmischesdomi.customitems.utils.inventory.menu.ClickableInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CustomItemsManagement {

	private final Set<ICustomTab> tabs = new HashSet<>();
	private final CustomItems plugin;

	private List<ClickableInventory> menuInventories;

	public CustomItemsManagement(CustomItems plugin) {
		this.plugin = plugin;
	}

	public void generateTabInventories() {
		menuInventories = InventoryGenerator.generateListInventory("§6Custom Item Tabs", Material.GRAY_STAINED_GLASS_PANE, tabs.toArray(new ClickableMenuItem[0]));
		tabs.forEach(ICustomTab::generateInventories);
	}

	public void openTabInventory(@Nonnull Player player, int page) {
		if (menuInventories.size() <= page) return;
		ClickableInventory clickableInventory = menuInventories.get(page);
		clickableInventory.open(player);
	}

	public void registerCustomTab(@Nonnull ICustomTab customTab) {
		tabs.add(customTab);
		if (CustomItems.getInstance().inventoriesGenerated()) generateTabInventories();
	}

	public ICustomItem getCustomItem(@Nonnull String key) {
		for (ICustomTab tab : tabs) {
			ICustomItem item = tab.getCustomItem(key);
			if (item != null) return item;
		}
		return null;
	}

}
