package net.kxmischesdomi.customitems.test.items;

import net.kxmischesdomi.customitems.item.type.RightInteractableCustomItem;
import net.kxmischesdomi.customitems.utils.bukkit.inventory.PlayerInventoryUtils;
import net.kxmischesdomi.customitems.utils.bukkit.item.ItemBuilder;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfo.ShapelessRecipeInfo;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfoContainer;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class BandagesItem extends RightInteractableCustomItem {

	@Nonnull
	@Override
	protected ItemStack createItemStack() {
		return ItemBuilder.createItem(Material.IRON_NUGGET, "§eBandages");
	}

	@Override
	public int getCustomModelData() {
		return 123;
	}

	@Override
	protected void createRecipes(@Nonnull RecipeInfoContainer container) {
		container.addRecipe(new ShapelessRecipeInfo().addIngredients(Material.PAPER, Material.PAPER, Material.STRING, Material.LEATHER));
	}

	@Nonnull
	@Override
	public String getKey() {
		return "bandages";
	}

	@Override
	public void onRightClick(@Nonnull PlayerInteractEvent event) {
		double maxHealth = event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
		if (event.getPlayer().getHealth() >= maxHealth) return;
		PlayerInventoryUtils.consumeItem(event);
		double newHealth = event.getPlayer().getHealth() + 6;
		if (newHealth >= maxHealth) newHealth = maxHealth;
		event.getPlayer().setHealth(newHealth);
	}

}
    