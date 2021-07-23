package net.kxmischesdomi.customitems.test.items;

import net.kxmischesdomi.customitems.CustomItems;
import net.kxmischesdomi.customitems.item.AbstractCustomItem;
import net.kxmischesdomi.customitems.management.scheduler.task.ScheduledTask;
import net.kxmischesdomi.customitems.utils.bukkit.item.ItemBuilder;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfoContainer;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class FusedLightningShardItem extends AbstractCustomItem {

	public boolean spawnShards = true;

	@Nonnull
	@Override
	protected ItemStack createItemStack() {
		return ItemBuilder.createItem(Material.PRISMARINE_SHARD, "§cFused Lightning Shard");
	}

	@Override
	public int getCustomModelData() {
		return 123;
	}

	@Override
	protected void createRecipes(@Nonnull RecipeInfoContainer container) {

	}

	@Nonnull
	@Override
	public String getKey() {
		return "fused_lightning_shard";
	}

	/**
	 * Spawns a Lightning Shard when a lighting strikes
	 */
	@EventHandler
	public void onLightningStrike(@Nonnull LightningStrikeEvent event) {
		if (!spawnShards) return;
		Location location = event.getLightning().getLocation();
		if (location.getWorld() == null) return;
		int y = location.getWorld().getHighestBlockYAt(location);
		location.setY(y + 1);
		location.getWorld().dropItem(location, getItemStack());
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity().getType() != EntityType.DROPPED_ITEM) return;
		if (event.getCause() != DamageCause.FIRE && event.getCause() != DamageCause.FIRE_TICK && event.getCause() != DamageCause.LAVA && event.getCause() != DamageCause.LIGHTNING) return;
		Item item = (Item) event.getEntity();
		if (!isCurrentItem(item.getItemStack())) return;
		event.setCancelled(true);
	}

	@ScheduledTask(ticks = 1)
	public void onTick() {
		for (World world : Bukkit.getWorlds()) {
			for (Entity entity : world.getEntities()) {
				if (!(entity instanceof Item)) continue;
				Item item = (Item) entity;
				if (!isCurrentItem(item.getItemStack())) continue;
				if (item.getLocation().getBlock().getType() == Material.WATER) {
					Bukkit.getScheduler().runTask(CustomItems.getInstance(), () -> {
						if (item.isDead()) return;

						ItemStack itemStack = getCustomItem("lightning_shard").getItemStack();
						itemStack.setAmount(1);
						Item shard = item.getWorld().dropItem(item.getLocation(), itemStack);
						shard.setVelocity(item.getVelocity());
						ItemStack shardItemStack = shard.getItemStack();
						shardItemStack.setAmount(item.getItemStack().getAmount());
						shard.setItemStack(shardItemStack);

						shard.getWorld().spawnParticle(Particle.SNEEZE, shard.getLocation().clone().add(0, 0.4, 0), 0);
						shard.getWorld().playSound(shard.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1, 0.9f);

						item.remove();
					});
				}
			}
		}
	}

	@ScheduledTask(ticks = 10)
	public void onHalfSecond() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			for (ItemStack stack : player.getInventory().getContents()) {
				applyDamage(player, stack);
			}
		}
	}

	private void applyDamage(@Nonnull Player player, ItemStack itemStack) {
		if (!isCurrentItem(itemStack)) return;
		Bukkit.getScheduler().runTask(getPlugin(), () -> player.damage(2));
	}

}
    