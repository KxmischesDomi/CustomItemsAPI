package net.kxmischesdomi.customitems.item.type;

import net.kxmischesdomi.customitems.CustomItems;
import net.kxmischesdomi.customitems.item.ICustomItem;
import net.kxmischesdomi.customitems.tab.AbstractCustomTab;
import net.kxmischesdomi.customitems.utils.bukkit.event.PlayerInventoryClickEvent;
import net.kxmischesdomi.customitems.utils.bukkit.item.ItemBuilder;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfoContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

/**
 * Empty placeholder item which can be used for a simple sorting in the {@link AbstractCustomTab} inventory
 *
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CustomTabPlaceHolderItem implements ICustomItem {

	@Nonnull
	@Override
	public ItemStack getItemStack() {
		return ItemBuilder.createItem(Material.GRAY_STAINED_GLASS_PANE, "§0");
	}

	@Nonnull
	@Override
	public String getKey() {
		return "placeholder";
	}

	@Nonnull
	@Override
	public String getDisplayName() {
		return "Placeholder";
	}

	@Nonnull
	@Override
	public RecipeInfoContainer getRecipe() {
		return new RecipeInfoContainer();
	}

	@Nonnull
	@Override
	public JavaPlugin getPlugin() {
		return CustomItems.getInstance();
	}

	@Nonnull
	@Override
	public ItemStack getDisplayItem() {
		return getItemStack();
	}

	@Override
	public void onMenuClick(PlayerInventoryClickEvent event) {

	}

}
    