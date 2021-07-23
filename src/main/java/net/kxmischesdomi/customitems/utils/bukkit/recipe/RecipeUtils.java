package net.kxmischesdomi.customitems.utils.bukkit.recipe;

import org.bukkit.inventory.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class RecipeUtils {

	public static Set<ItemStack> getRecipeIngredients(Recipe recipe) {
		Set<ItemStack> set = new HashSet<>();

		if (recipe instanceof ShapelessRecipe) {
			set.addAll(((ShapelessRecipe) recipe).getIngredientList());
		} else if (recipe instanceof ShapedRecipe) {
			set.addAll(((ShapedRecipe) recipe).getIngredientMap().values());
		} else if (recipe instanceof FurnaceRecipe) {
			set.add(((FurnaceRecipe) recipe).getInput());
		}

		return set;
	}

}
    