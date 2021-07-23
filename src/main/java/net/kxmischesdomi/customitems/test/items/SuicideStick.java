package net.kxmischesdomi.customitems.test.items;

import net.kxmischesdomi.customitems.item.type.InteractableCustomItem;
import net.kxmischesdomi.customitems.utils.bukkit.item.ItemBuilder;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfo.ShapelessRecipeInfo;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfoContainer;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class SuicideStick extends InteractableCustomItem {

	@Nonnull
	@Override
	protected ItemStack createItemStack() {
		return ItemBuilder.createItem(Material.STICK, "§eSuicide Stick");
	}

	@Override
	public int getCustomModelData() {
		return 0;
	}

	@Override
	protected void createRecipes(@Nonnull RecipeInfoContainer container) {
		container.addRecipe(new ShapelessRecipeInfo().addIngredients(Material.STICK, Material.POISONOUS_POTATO));
	}

	@Nonnull
	@Override
	public String getKey() {
		return "suicidestaff";
	}

	@Override
	public void onPlayerInteract(@Nonnull PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		event.getPlayer().setHealth(0);
	}

}
    