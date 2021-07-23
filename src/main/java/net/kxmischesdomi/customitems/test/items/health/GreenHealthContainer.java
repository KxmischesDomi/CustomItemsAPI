package net.kxmischesdomi.customitems.test.items.health;

import net.kxmischesdomi.customitems.utils.bukkit.EntityUtils;
import net.kxmischesdomi.customitems.utils.bukkit.item.ItemBuilder;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfoContainer;
import org.bukkit.Material;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class GreenHealthContainer extends HealthContainerItem {

	@Nonnull
	@Override
	protected ItemStack createItemStack() {
		return ItemBuilder.createItem(Material.IRON_NUGGET, "Green Heart");
	}

	@Override
	public int getCustomModelData() {
		return 126;
	}

	@Override
	protected void createRecipes(@Nonnull RecipeInfoContainer container) {

	}

	@Nonnull
	@Override
	public String getKey() {
		return "green_heart";
	}

	@Override
	public boolean spawnItem(@Nonnull EntityDeathEvent event) {
		if (!EntityUtils.isBoos(event.getEntityType())) return false;

		return trueAtPercent(50);
	}

	@Override
	public int getHealthPerContainer() {
		return 4;
	}

	@Override
	public int getMaxContainer() {
		return 20;
	}

}
    