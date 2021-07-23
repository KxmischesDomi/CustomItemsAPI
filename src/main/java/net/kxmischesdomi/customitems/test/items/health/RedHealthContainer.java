package net.kxmischesdomi.customitems.test.items.health;

import net.kxmischesdomi.customitems.utils.bukkit.EntityUtils;
import net.kxmischesdomi.customitems.utils.bukkit.item.ItemBuilder;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfoContainer;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class RedHealthContainer extends HealthContainerItem {

	@Nonnull
	@Override
	protected ItemStack createItemStack() {
		return ItemBuilder.createItem(Material.IRON_NUGGET, "Red Heart");
	}

	@Override
	public int getCustomModelData() {
		return 124;
	}

	@Override
	protected void createRecipes(@Nonnull RecipeInfoContainer container) {

	}

	@Nonnull
	@Override
	public String getKey() {
		return "red_heart";
	}

	@Override
	public boolean spawnItem(@Nonnull EntityDeathEvent event) {
		EntityType type = event.getEntity().getType();
		if (EntityUtils.isNetherMob(type) || EntityUtils.isEndMob(type) || EntityUtils.isBoos(type)) {
			if (type==EntityType.SKELETON && event.getEntity().getWorld().getEnvironment()==Environment.NETHER) return false;
		}

		return trueAtPercent(4);
	}

	@Override
	public int getHealthPerContainer() {
		return 1;
	}

	@Override
	public int getMaxContainer() {
		return 20;
	}
}
    