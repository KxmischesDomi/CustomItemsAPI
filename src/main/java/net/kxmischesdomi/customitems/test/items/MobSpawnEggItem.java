package net.kxmischesdomi.customitems.test.items;

import net.kxmischesdomi.customitems.item.type.CustomItemSpawnEgg;
import net.kxmischesdomi.customitems.utils.bukkit.item.ItemBuilder;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfoContainer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.BiFunction;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class MobSpawnEggItem extends CustomItemSpawnEgg {

	private final String key;
	private final String name;
	private final Material material;
	private final BiFunction<World, Location, Entity> spawnConsumer;

	public MobSpawnEggItem(@Nonnull String key, @Nonnull String displayName, @Nonnull Material material, @Nonnull BiFunction<World, Location, Entity> spawnConsumer) {
		this.key = key;
		this.name = displayName;
		this.material = material;
		this.spawnConsumer = spawnConsumer;
	}

	@Nonnull
	@Override
	protected ItemStack createItemStack() {
		return ItemBuilder.createItem(material, name);
	}

	@Override
	public int getCustomModelData() {
		return 0;
	}

	@Override
	protected void createRecipes(@Nonnull RecipeInfoContainer container) {

	}

	@Override
	public Entity spawnEntity(@Nonnull World world, @Nonnull Location location) {
		return spawnConsumer.apply(world, location);
	}

	@Nonnull
	@Override
	public String getKey() {
		return key;
	}
}
    