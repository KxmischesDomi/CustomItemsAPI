package net.kxmischesdomi.customitems.api.item;

import de.tr7zw.nbtapi.NBTItem;
import net.kxmischesdomi.customitems.CustomItemConstants;
import net.kxmischesdomi.customitems.utils.event.PlayerInventoryClickEvent;
import net.kxmischesdomi.customitems.utils.inventory.generation.InventoryGenerator;
import net.kxmischesdomi.customitems.utils.inventory.menu.ClickableInventory;
import net.kxmischesdomi.customitems.utils.recipe.CustomItemRecipe;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public abstract class CustomItem implements ICustomItem, Listener {

	private final ClickableInventory itemInventory = InventoryGenerator.generateItemInfoInventory(this);

	private ItemStack itemStack;
	private CustomItemRecipe itemRecipe;

	@Nonnull
	@Override
	public final ItemStack getItemStack() {
		NBTItem nbtItem = new NBTItem(getDisplayItem());
		nbtItem.setString(CustomItemConstants.KEY, getKey());
		nbtItem.setInteger(CustomItemConstants.CUSTOM_MODEL_DATA, getCustomModelData());
		return nbtItem.getItem();
	}

	@Nonnull
	@Override
	public final ItemStack getDisplayItem() {
		if (itemStack == null) itemStack = createItemStack();
		return itemStack;
	}

	@Nonnull
	@Override
	public String getDisplayName() {
		ItemStack item = getDisplayItem();
		return item.getItemMeta() == null ? "Null" : item.getItemMeta().getDisplayName();
	}

	@Override
	public void onMenuClick(PlayerInventoryClickEvent event) {
		itemInventory.open(event.getPlayer());
	}

	@Nullable
	@Override
	public final CustomItemRecipe getRecipe() {
		if (itemRecipe == null) itemRecipe = createRecipe();
		return itemRecipe;
	}

	@Nonnull protected abstract ItemStack createItemStack();
	@Nonnegative public abstract int getCustomModelData();
	@Nullable protected abstract CustomItemRecipe createRecipe();

	public boolean isCurrentItem(@Nonnull ItemStack itemStack) {
		NBTItem nbtItem = new NBTItem(itemStack);
		return nbtItem.getString(CustomItemConstants.KEY).equals(getKey());
	}

}
    