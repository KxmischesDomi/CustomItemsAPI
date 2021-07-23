package net.kxmischesdomi.customitems.management.menu;

import de.tr7zw.nbtapi.NBTItem;
import net.kxmischesdomi.customitems.CustomItems;
import net.kxmischesdomi.customitems.item.ICustomItem;
import net.kxmischesdomi.customitems.utils.bukkit.customitems.CustomItemUtils;
import net.kxmischesdomi.customitems.utils.bukkit.event.PlayerInventoryClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.*;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CustomItemRecipeMenu implements MenuPosition {

	protected final String title;
	protected ItemStack currentRecipe;
	protected int currentRecipeCount = 0;
	protected int loops = 0;

	public CustomItemRecipeMenu(String title) {
		this.title = title;
	}

	@Override
	public void handleClick(@Nonnull PlayerInventoryClickEvent event) {
		if (event.isRightClick()) return;
		if (event.getCurrentItem() == null) return;

		if (!isItem(event.getCurrentItem(), currentRecipe)) {
			currentRecipe = event.getCurrentItem();
			currentRecipeCount = -1;
		}

		open(event.getPlayer(), currentRecipe);
	}

	public void open(@Nonnull Player player, ItemStack itemStack) {
		Inventory inventory = createInventory(itemStack);
		if (inventory == null) return;
		MenuPosition.set(player, this);
		player.openInventory(inventory);
	}

	public Inventory createInventory(@Nonnull ItemStack itemStack) {
		Recipe recipe = getNextRecipe(itemStack);
		if (recipe == null) return null;

		Inventory inventory = Bukkit.createInventory(MenuPosition.HOLDER, InventoryType.WORKBENCH, title);

		if (recipe instanceof ShapedRecipe) {
			fillShapedInventory(((ShapedRecipe) recipe), inventory);
		} else if (recipe instanceof ShapelessRecipe) {
			fillShapelessInventory(((ShapelessRecipe) recipe), inventory);
		} else if (recipe instanceof FurnaceRecipe) {
			inventory = Bukkit.createInventory(HOLDER, InventoryType.FURNACE, title);
			fillFurnaceInventory(((FurnaceRecipe) recipe), inventory);
		} else {
			inventory = createInventory(itemStack);
			loops = 0;
		}

		return inventory;
	}

	private Recipe getNextRecipe(@Nonnull ItemStack itemStack) {
		currentRecipeCount++;

		List<Recipe> recipes = getRecipes(itemStack);

		if (loops > recipes.size()) return null;

		if (currentRecipeCount >= recipes.size()) currentRecipeCount = 0;
		return recipes.get(currentRecipeCount);
	}

	private boolean isItem(@Nonnull ItemStack i1, @Nonnull ItemStack i2) {
		if (!i1.isSimilar(i2)) return false;
		return new NBTItem(i1).equals(new NBTItem(i2));
	}

	private List<Recipe> getRecipes(@Nonnull ItemStack itemStack) {
		String key = CustomItemUtils.getKeyFromItemStack(itemStack);
		if (key == null) return getVanillaRecipes(itemStack);
		ICustomItem item = CustomItems.getInstance().getCustomItemsManagement().getCustomItem(key);
		if (item == null) return getVanillaRecipes(itemStack);
		return item.getRecipe().getCreatedRecipes();
	}

	private List<Recipe> getVanillaRecipes(@Nonnull ItemStack itemStack) {
		return Bukkit.getRecipesFor(itemStack);
	}

	private void fillShapedInventory(@Nonnull ShapedRecipe recipe, @Nonnull Inventory inventory) {
		inventory.setItem(0, recipe.getResult());

		String[] recipeShape = recipe.getShape();
		Map<Character, ItemStack> ingredientMap = recipe.getIngredientMap();

		for (int j = 0; j < recipeShape.length; j++) {
			for (int k = 0; k < recipeShape[j].length(); k++) {
				ItemStack item = ingredientMap.get(recipeShape[j].toCharArray()[k]);
				if (item == null) continue;
				inventory.setItem(j * 3 + k + 1, item);

			}

		}

	}

	private void fillShapelessInventory(@Nonnull ShapelessRecipe recipe, @Nonnull Inventory inventory) {
		inventory.setItem(0, recipe.getResult());

		List<ItemStack> ingredientList = recipe.getIngredientList();
		for (int i = 0; i < ingredientList.size(); i++) {
			ItemStack item = ingredientList.get(i);
			inventory.setItem(i + 1, item);

		}
	}

	private void fillFurnaceInventory(@Nonnull FurnaceRecipe recipe, @Nonnull Inventory inventory) {
		inventory.setItem(2, recipe.getResult());
		inventory.setItem(1, new ItemStack(Material.COAL));
		inventory.setItem(0, recipe.getInput());
	}

}
