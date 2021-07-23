package net.kxmischesdomi.customitems.test.items;

import net.kxmischesdomi.customitems.item.type.FoodItem;
import net.kxmischesdomi.customitems.utils.bukkit.item.ItemBuilder;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfo.ShapedRecipeInfo;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfoContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class BurgerItem extends FoodItem {

	@Nonnull
	@Override
	protected ItemStack createItemStack() {
		return ItemBuilder.createItem(Material.BREAD, "Burger");
	}

	@Override
	public int getCustomModelData() {
		return 123;
	}

	@Override
	protected void createRecipes(@Nonnull RecipeInfoContainer container) {
		container.addRecipe(
				new ShapedRecipeInfo()
						.setShape(" b ", "wsw", " b ")
						.setIngredient('b', Material.BREAD)
						.setIngredient('s', Material.COOKED_BEEF)
						.setIngredient('w', Material.WHEAT_SEEDS, Material.BEETROOT_SEEDS, Material.MELON_SEEDS, Material.PUMPKIN_SEEDS));
	}

	@Nonnull
	@Override
	public String getKey() {
		return "burger";
	}

	@Override
	public int getFoodLevels() {
		return 7;
	}

	@Override
	public int getSaturation() {
		return 20;
	}

}
    