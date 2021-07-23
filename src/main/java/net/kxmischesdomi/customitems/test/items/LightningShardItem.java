package net.kxmischesdomi.customitems.test.items;

import net.kxmischesdomi.customitems.item.type.RightInteractableCustomItem;
import net.kxmischesdomi.customitems.utils.bukkit.item.ItemBuilder;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfoContainer;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class LightningShardItem extends RightInteractableCustomItem {

	private final FusedLightningShardItem fusedLightningShard = (FusedLightningShardItem) getCustomItem("fused_lightning_shard");

	@Nonnull
	@Override
	protected ItemStack createItemStack() {
		return ItemBuilder.createItem(Material.PRISMARINE_SHARD, "§bLightning Shard");
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
		return "lightning_shard";
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity().getType() != EntityType.DROPPED_ITEM) return;
		Item item = (Item) event.getEntity();
		if (!isCurrentItem(item.getItemStack())) return;
		if (event.getCause() != DamageCause.FIRE && event.getCause() != DamageCause.FIRE_TICK && event.getCause() != DamageCause.LAVA && event.getCause() != DamageCause.LIGHTNING) return;
		fusedLightningShard.spawnShards = false;
		for (int i = 0; i < item.getItemStack().getAmount(); i++) {
			onItemBurn(item);
		}
		fusedLightningShard.spawnShards = true;
	}

	public void onItemBurn(@Nonnull Item item) {
		item.getWorld().strikeLightning(item.getLocation());
		item.remove();
	}

	@Override
	public void onPlayerInteractEntity(@Nonnull PlayerInteractEntityEvent event) {
		event.setCancelled(false);
	}

	@Override
	public void onRightClick(@Nonnull PlayerInteractEvent event) {
		event.setUseItemInHand(Result.DEFAULT);
	}

}
    