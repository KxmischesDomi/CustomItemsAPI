package net.kxmischesdomi.customitems.utils.bukkit.recipe;

import net.kxmischesdomi.customitems.item.ICustomItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;
import org.bukkit.inventory.RecipeChoice.ExactChoice;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public interface RecipeInfo {

	default void apply(@Nonnull RecipeBuilder recipeBuilder) {
		apply(recipeBuilder.getRecipe());
	}

	void apply(@Nonnull Recipe recipe);

	@Nonnull default Recipe createRecipe(@Nonnull ICustomItem customItem) {
		return createRecipe(customItem, customItem.getKey());
	}
	@Nonnull Recipe createRecipe(@Nonnull ICustomItem customItem, @Nonnull String key);

	class ShapedRecipeInfo implements RecipeInfo {

		private final Map<Character, RecipeChoice> ingredients = new HashMap<>();
		private String[] shape;

		public ShapedRecipeInfo setShape(@Nonnull String... shape) {
			this.shape = shape;
			return this;
		}

		public ShapedRecipeInfo setIngredient(char key, @Nonnull Material... material) {
			ingredients.put(key, new MaterialChoice(material));
			return this;
		}

		public ShapedRecipeInfo setIngredient(char key, @Nonnull ItemStack... itemStack) {
			ingredients.put(key, new ExactChoice(itemStack));
			return this;
		}

		public ShapedRecipeInfo setIngredient(char key, @Nonnull RecipeChoice choice) {
			ingredients.put(key, choice);
			return this;
		}

		public void apply(@Nonnull Recipe recipe) {
			if (!(recipe instanceof ShapedRecipe)) return;
			ShapedRecipe shapedRecipe = (ShapedRecipe) recipe;
			shapedRecipe.shape(shape);
			for (Entry<Character, RecipeChoice> entry : ingredients.entrySet()) {
				shapedRecipe.setIngredient(entry.getKey(), entry.getValue());
			}
		}

		@Nonnull
		@Override
		public Recipe createRecipe(@Nonnull ICustomItem customItem) {
			return createRecipe(customItem, customItem.getKey());
		}

		@Nonnull
		@Override
		public Recipe createRecipe(@Nonnull ICustomItem customItem, @Nonnull String key) {
			ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(customItem.getPlugin(), key), customItem.getItemStack());
			apply(shapedRecipe);
			return shapedRecipe;
		}

	}

	class ShapelessRecipeInfo implements RecipeInfo {

		private final List<RecipeChoice> ingredients = new ArrayList<>();

		public ShapelessRecipeInfo addIngredients(@Nonnull Material... materials) {
			for (Material material : materials) {
				ingredients.add(new MaterialChoice(material));
			}
			return this;
		}

		public ShapelessRecipeInfo addIngredients(@Nonnull ItemStack... itemStacks) {
			ingredients.add(new ExactChoice(itemStacks));
			return this;
		}

		@Override
		public void apply(@Nonnull Recipe recipe) {
			if (!(recipe instanceof ShapelessRecipe)) return;
			ShapelessRecipe shapelessRecipe = (ShapelessRecipe) recipe;
			for (RecipeChoice ingredient : ingredients) {
				shapelessRecipe.addIngredient(ingredient);
			}
		}

		@Nonnull
		@Override
		public Recipe createRecipe(@Nonnull ICustomItem customItem, @Nonnull String key) {
			ShapelessRecipe shapelessRecipe = new ShapelessRecipe(new NamespacedKey(customItem.getPlugin(), key), customItem.getItemStack());
			apply(shapelessRecipe);
			return shapelessRecipe;
		}

	}

	class FurnaceRecipeInfo implements RecipeInfo {

		RecipeChoice input;
		float experience = 0;
		int cookingTime = 0;

		public FurnaceRecipeInfo(ItemStack input) {
			this.input = new ExactChoice(input);
		}

		public FurnaceRecipeInfo(Material input) {
			this.input = new MaterialChoice(input);
		}

		public FurnaceRecipeInfo(RecipeChoice input) {
			this.input = input;
		}

		public void setInput(RecipeChoice input) {
			this.input = input;
		}

		public void setExperience(float experience) {
			this.experience = experience;
		}

		public void setCookingTime(int cookingTime) {
			this.cookingTime = cookingTime;
		}

		@Override
		public void apply(@Nonnull Recipe recipe) {
			if (!(recipe instanceof FurnaceRecipe)) return;
			FurnaceRecipe furnaceRecipe = (FurnaceRecipe) recipe;
			furnaceRecipe.setInputChoice(input);
			furnaceRecipe.setExperience(experience);
			furnaceRecipe.setCookingTime(cookingTime);
		}

		@Nonnull
		@Override
		public Recipe createRecipe(@Nonnull ICustomItem customItem, @Nonnull String key) {
			return new FurnaceRecipe(new NamespacedKey(customItem.getPlugin(), key), customItem.getItemStack(), input, experience, cookingTime);
		}

	}


}
    