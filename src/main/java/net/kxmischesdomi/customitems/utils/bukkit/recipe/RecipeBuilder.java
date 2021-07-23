package net.kxmischesdomi.customitems.utils.bukkit.recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;
import org.bukkit.inventory.RecipeChoice.ExactChoice;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public abstract class RecipeBuilder {

	private static JavaPlugin defaultPlugin;

	private final NamespacedKey namespacedKey;

	public RecipeBuilder(@Nonnull String key) {
		namespacedKey = new NamespacedKey(defaultPlugin, key);
	}

	public RecipeBuilder(@Nonnull JavaPlugin defaultPlugin, @Nonnull String key) {
		namespacedKey = new NamespacedKey(defaultPlugin, key);
	}

	public static void setDefaultPlugin(JavaPlugin defaultPlugin) {
		RecipeBuilder.defaultPlugin = defaultPlugin;
	}

	public void registerRecipe() {
		Bukkit.getServer().addRecipe(getRecipe());
	}

	public abstract Recipe getRecipe();

	protected NamespacedKey getNamespacedKey() {
		return namespacedKey;
	}

	public static class ShapedRecipeBuilder extends RecipeBuilder {

		private final ShapedRecipe recipe;

		public ShapedRecipeBuilder(@Nonnull String key, @Nonnull ItemStack resultItem) {
			super(key);
			recipe = new ShapedRecipe(getNamespacedKey(), resultItem);
		}

		public ShapedRecipeBuilder setShape(@Nonnull String... shape) {
			recipe.shape(shape);
			return this;
		}

		public ShapedRecipeBuilder setIngredient(char key, @Nonnull Material material) {
			recipe.setIngredient(key, material);
			return this;
		}

		public ShapedRecipeBuilder setIngredient(char key, @Nonnull ItemStack itemStack) {
			recipe.setIngredient(key, new ExactChoice(itemStack));
			return this;
		}

		@Override
		public ShapedRecipe getRecipe() {
			return recipe;
		}

	}

	public static class ShapelessRecipeBuilder extends RecipeBuilder {

		private final ShapelessRecipe recipe;

		public ShapelessRecipeBuilder(@Nonnull String key, @Nonnull ItemStack resultItem) {
			super(key);
			recipe = new ShapelessRecipe(getNamespacedKey(), resultItem);
		}

		public ShapelessRecipeBuilder(@Nonnull String key, @Nonnull ItemStack resultItem, Material... materials) {
			super(key);
			recipe = new ShapelessRecipe(getNamespacedKey(), resultItem);
			addIngredients(materials);
		}

		public ShapelessRecipeBuilder(@Nonnull String key, @Nonnull ItemStack resultItem, ItemStack... itemStacks) {
			super(key);
			recipe = new ShapelessRecipe(getNamespacedKey(), resultItem);
			addIngredients(itemStacks);
		}

		public ShapelessRecipeBuilder addIngredients(@Nonnull Material... materials) {
			for (Material material : materials) {
				recipe.addIngredient(material);
			}
			return this;
		}

		public ShapelessRecipeBuilder addIngredients(@Nonnull ItemStack... itemStacks) {
			recipe.addIngredient(new ExactChoice(itemStacks));
			return this;
		}

		@Override
		public ShapelessRecipe getRecipe() {
			return recipe;
		}

	}

	public static class FurnaceRecipeBuilder extends RecipeBuilder {

		private final FurnaceRecipe recipe;

		public FurnaceRecipeBuilder(@Nonnull String key, @Nonnull ItemStack resultItem, ItemStack source, float experience, int cookingTime) {
			super(key);
			recipe = new FurnaceRecipe(getNamespacedKey(), resultItem, new ExactChoice(source), experience, cookingTime);
		}

		public FurnaceRecipeBuilder(@Nonnull String key, @Nonnull ItemStack resultItem, Material source, float experience, int cookingTime) {
			super(key);
			recipe = new FurnaceRecipe(getNamespacedKey(), resultItem, new MaterialChoice(source), experience, cookingTime);
		}

		@Override
		public FurnaceRecipe getRecipe() {
			return recipe;
		}

	}

}