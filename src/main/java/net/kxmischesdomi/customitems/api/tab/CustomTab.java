package net.kxmischesdomi.customitems.api.tab;

import net.kxmischesdomi.customitems.CustomItems;
import net.kxmischesdomi.customitems.api.item.ICustomItem;
import net.kxmischesdomi.customitems.utils.event.PlayerInventoryClickEvent;
import net.kxmischesdomi.customitems.utils.inventory.generation.ClickableMenuItem;
import net.kxmischesdomi.customitems.utils.inventory.generation.InventoryGenerator;
import net.kxmischesdomi.customitems.utils.inventory.menu.ClickableInventory;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CustomTab implements ICustomTab {

	private final List<ICustomItem> items = new ArrayList<>();
	private final ItemStack displayItem;

	private List<ClickableInventory> menuInventories;

	public CustomTab(@Nonnull ItemStack displayItem) {
		this.displayItem = displayItem;
	}

	@Nonnull
	@Override
	public ItemStack getDisplayItem() {
		return displayItem;
	}

	@Override
	public void onMenuClick(PlayerInventoryClickEvent event) {
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
		if (customItem.getRecipe() != null) customItem.getRecipe().registerRecipes();
		if (CustomItems.getInstance().inventoriesGenerated()) generateInventories();
		if (customItem instanceof Listener) CustomItems.getInstance().registerListener(((Listener) customItem));
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
		menuInventories = InventoryGenerator.generateListInventory(meta == null ? "§cNull" : meta.getDisplayName() , Material.GRAY_STAINED_GLASS_PANE, items.toArray(new ClickableMenuItem[0]));
	}

}
    