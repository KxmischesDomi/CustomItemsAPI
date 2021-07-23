package net.kxmischesdomi.customitems.item;

import net.kxmischesdomi.customitems.utils.bukkit.inventory.generation.ClickableMenuItem;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfoContainer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public interface ICustomItem extends ClickableMenuItem {

	@Nonnull
	ItemStack getItemStack();

	@Nonnull
	String getKey();

	@Nonnull
	String getDisplayName();

	@Nonnull
	RecipeInfoContainer getRecipe();

	@Nonnull
	JavaPlugin getPlugin();

	default boolean allowVanillaCrafting() {
		return false;
	}

}
