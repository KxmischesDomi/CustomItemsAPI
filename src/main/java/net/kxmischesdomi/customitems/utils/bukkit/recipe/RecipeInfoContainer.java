package net.kxmischesdomi.customitems.utils.bukkit.recipe;

import net.kxmischesdomi.customitems.item.ICustomItem;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Recipe;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class RecipeInfoContainer {

	private final List<Recipe> createdRecipes = new ArrayList<>();

	public List<RecipeInfo> recipeInfos = new LinkedList<>();

	public RecipeInfoContainer addRecipe(@Nonnull RecipeInfo recipeInfo) {
		recipeInfos.add(recipeInfo);
		return this;
	}

	public RecipeInfoContainer removeRecipe(@Nonnull RecipeInfo recipeInfo) {
		recipeInfos.remove(recipeInfo);
		return this;
	}

	public boolean isEmpty() {
		return recipeInfos.isEmpty();
	}

	public List<RecipeInfo> getRecipeInfos() {
		return recipeInfos;
	}

	public void registerRecipes(ICustomItem customItem) {
		int i = 0;
		for (RecipeInfo info : recipeInfos) {
			Recipe recipe = info.createRecipe(customItem, customItem.getKey() + "_" + i);
			createdRecipes.add(recipe);
			Bukkit.addRecipe(recipe);
			i++;
		}
	}

	public List<Recipe> getCreatedRecipes() {
		return createdRecipes;
	}

}
    