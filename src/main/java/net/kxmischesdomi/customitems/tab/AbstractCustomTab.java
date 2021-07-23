package net.kxmischesdomi.customitems.tab;

import net.kxmischesdomi.customitems.CustomItems;
import net.kxmischesdomi.customitems.item.ICustomItem;
import net.kxmischesdomi.customitems.utils.bukkit.event.PlayerInventoryClickEvent;
import net.kxmischesdomi.customitems.utils.bukkit.inventory.generation.ClickableMenuItem;
import net.kxmischesdomi.customitems.utils.bukkit.inventory.generation.InventoryGenerator;
import net.kxmischesdomi.customitems.management.menu.ClickableInventory;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public abstract class AbstractCustomTab implements ICustomTab {

	private final List<ICustomItem> items = new ArrayList<>();

	private List<ClickableInventory> menuInventories;

	@Override
	public void onMenuClick(PlayerInventoryClickEvent event) {
		if (event.getClick() != ClickType.LEFT && event.getClick() != ClickType.SHIFT_LEFT) return;
		getInventoryPage(0).open(event.getPlayer());
	}

	@Nonnull
	@Override
	public ClickableInventory getInventoryPage(int page) {
		if (page >= menuInventories.size()) return menuInventories.get(0);
		return menuInventories.get(page);
	}

	@Override
	public void registerCustomItem(@Nonnull ICustomItem customItem) {
		items.add(customItem);
		if (!customItem.getRecipe().isEmpty()) customItem.getRecipe().registerRecipes(customItem);
		if (CustomItems.getInstance().inventoriesGenerated()) generateInventories();
		if (customItem instanceof Listener) CustomItems.getInstance().registerListener(((Listener) customItem));
		CustomItems.getInstance().getScheduleManager().register(customItem);
	}

	@Override
	public ICustomItem getCustomItem(@Nonnull String key) {
		for (ICustomItem item : items) {
			if (item.getKey().equals(key)) return item;
		}
		return null;
	}

	@Override
	public void generateInventories() {
		ItemMeta meta = getDisplayItem().getItemMeta();
		menuInventories = InventoryGenerator.generateListInventory(meta == null ? "§cNull" : meta.getDisplayName(), Material.GRAY_STAINED_GLASS_PANE, items.toArray(new ClickableMenuItem[0]));
	}

}
    