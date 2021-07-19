package net.kxmischesdomi.customitems.test;

import net.kxmischesdomi.customitems.api.item.InteractCustomItem;
import net.kxmischesdomi.customitems.utils.item.ItemBuilder;
import net.kxmischesdomi.customitems.utils.recipe.CustomItemRecipe;
import net.kxmischesdomi.customitems.utils.recipe.RecipeBuilder.ShapelessRecipeBuilder;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class SuicideStaff extends InteractCustomItem {

	@Nonnull
	@Override
	protected ItemStack createItemStack() {
		return ItemBuilder.createItem(Material.STICK, "§cSuicide Staff");
	}

	@Override
	public int getCustomModelData() {
		return 0;
	}

	@Nullable
	@Override
	protected CustomItemRecipe createRecipe() {
		return new CustomItemRecipe().addRecipe(new ShapelessRecipeBuilder("suicidestaff", getItemStack()).addIngredient(Material.STICK, Material.FLINT_AND_STEEL));
	}

	@Nonnull
	@Override
	public String getKey() {
		return "suicidestaff";
	}

	@Override
	public void onInteract(@Nonnull PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		event.getPlayer().setHealth(0);
	}

}
    