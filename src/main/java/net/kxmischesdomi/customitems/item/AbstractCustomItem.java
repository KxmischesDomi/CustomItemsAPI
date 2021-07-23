package net.kxmischesdomi.customitems.item;

import de.tr7zw.nbtapi.NBTItem;
import net.kxmischesdomi.customitems.CustomItemConstants;
import net.kxmischesdomi.customitems.CustomItems;
import net.kxmischesdomi.customitems.item.attribute.CustomModelDataAttribute;
import net.kxmischesdomi.customitems.item.attribute.IAttribute;
import net.kxmischesdomi.customitems.item.attribute.KeyAttribute;
import net.kxmischesdomi.customitems.utils.bukkit.event.PlayerInventoryClickEvent;
import net.kxmischesdomi.customitems.utils.bukkit.inventory.generation.InventoryGenerator;
import net.kxmischesdomi.customitems.utils.bukkit.item.ItemBuilder;
import net.kxmischesdomi.customitems.utils.bukkit.item.ItemUtils;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfoContainer;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public abstract class AbstractCustomItem implements ICustomItem, Listener {

	protected ItemStack itemStack;
	private RecipeInfoContainer recipeContainer;
	private IAttribute[] attributes;

	public AbstractCustomItem(IAttribute... attributes) {
		ArrayList<IAttribute> arrayList = new ArrayList<>();
		arrayList.add(new CustomModelDataAttribute(this::getCustomModelData));
		arrayList.add(new KeyAttribute(this::getKey));
		arrayList.addAll(Arrays.asList(attributes));
		this.attributes = arrayList.toArray(new IAttribute[0]);
	}

	public static ICustomItem getCustomItem(@Nonnull String key) {
		return CustomItems.getInstance().getCustomItemsManagement().getCustomItem(key);
	}

	/**
	 * Adds attributes to the item and sets {@link AbstractCustomItem#itemStack} to null to reset its attributes
	 */
	public void addAttributes(IAttribute... attributes) {
		itemStack = null;
		this.attributes = (IAttribute[]) ArrayUtils.addAll(this.attributes, attributes);
	}

	@Nonnull
	@Override
	public final ItemStack getItemStack() {
		if (itemStack == null) itemStack = ItemBuilder.applyAttributes(createItemStack(), attributes);
		ItemStack clone = itemStack.clone();
		clone.setAmount(1);
		return clone;
	}

	@Nonnull
	@Override
	public String getDisplayName() {
		ItemStack item = getDisplayItem();
		return item.getItemMeta() == null ? "§7null" : "§7" + item.getItemMeta().getDisplayName();
	}

	@Nonnull
	@Override
	public final RecipeInfoContainer getRecipe() {
		if (recipeContainer == null) {
			recipeContainer = new RecipeInfoContainer();
			createRecipes(recipeContainer);
		}
		return recipeContainer;
	}

	@Nonnull
	@Override
	public JavaPlugin getPlugin() {
		return CustomItems.getInstance();
	}

	@Nonnull
	@Override
	public final ItemStack getDisplayItem() {
		return getItemStack();
	}

	@Override
	public void onMenuClick(PlayerInventoryClickEvent event) {
		if (event.getClick() != ClickType.LEFT && event.getClick() != ClickType.SHIFT_LEFT) {
			ItemStack itemStack = getItemStack();
			if (event.isShiftClick()) itemStack.setAmount(itemStack.getType().getMaxStackSize());
			event.getPlayer().getInventory().addItem(itemStack);
			return;
		}
		InventoryGenerator.generateItemInfoInventory(this).open(event.getPlayer());
	}

	@Nonnull
	protected abstract ItemStack createItemStack();

	@Nonnegative
	public abstract int getCustomModelData();

	protected abstract void createRecipes(@Nonnull RecipeInfoContainer container);

	public boolean isCurrentItem(@Nullable ItemStack itemStack) {
		if (ItemUtils.isAir(itemStack)) return false;
		NBTItem nbtItem = new NBTItem(itemStack);
		return nbtItem.getString(CustomItemConstants.KEY).equals(getKey());
	}

}
    