package net.kxmischesdomi.customitems.utils.recipe;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CustomItemRecipe {

	private static JavaPlugin defaultPlugin;

	private final List<RecipeBuilder> recipeBuilders = new ArrayList<>();

	public CustomItemRecipe addRecipe(@Nonnull RecipeBuilder recipeBuilder) {
		recipeBuilders.add(recipeBuilder);
		return this;
	}

	public CustomItemRecipe removeRecipe(@Nonnull RecipeBuilder recipeBuilder) {
		recipeBuilders.remove(recipeBuilder);
		return this;
	}

	public CustomItemRecipe clearRecipes() {
		recipeBuilders.clear();
		return this;
	}

	public void registerRecipes() {
		for (RecipeBuilder recipeBuilder : recipeBuilders) {
			Bukkit.addRecipe(recipeBuilder.getRecipe());
		}
	}

	public List<RecipeBuilder> getRecipeBuilders() {
		return recipeBuilders;
	}

	public static void setDefaultPlugin(JavaPlugin defaultPlugin) {
		CustomItemRecipe.defaultPlugin = defaultPlugin;
	}

}
