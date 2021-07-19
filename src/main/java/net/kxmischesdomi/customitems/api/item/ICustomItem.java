package net.kxmischesdomi.customitems.api.item;

import net.kxmischesdomi.customitems.utils.inventory.generation.ClickableMenuItem;
import net.kxmischesdomi.customitems.utils.recipe.CustomItemRecipe;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public interface ICustomItem extends ClickableMenuItem {

	@Nonnull ItemStack getItemStack();
	@Nonnull String getKey();
	@Nonnull String getDisplayName();
	@Nullable CustomItemRecipe getRecipe();
	default boolean allowVanillaCrafting() { return false; }

}
