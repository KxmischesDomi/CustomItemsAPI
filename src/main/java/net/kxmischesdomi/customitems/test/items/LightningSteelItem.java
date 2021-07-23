package net.kxmischesdomi.customitems.test.items;

import net.kxmischesdomi.customitems.CustomItems;
import net.kxmischesdomi.customitems.utils.bukkit.BlockUtils;
import net.kxmischesdomi.customitems.utils.bukkit.inventory.DispenserInventoryUtils;
import net.kxmischesdomi.customitems.utils.bukkit.inventory.PlayerInventoryUtils;
import net.kxmischesdomi.customitems.utils.bukkit.item.ItemBuilder;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfo.ShapelessRecipeInfo;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfoContainer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Item;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class LightningSteelItem extends LightningShardItem {

	private final FusedLightningShardItem fusedLightningShard = (FusedLightningShardItem) getCustomItem("fused_lightning_shard");
	private final LightningShardItem lightningShard = (LightningShardItem) getCustomItem("lightning_shard");

	@Override
	public void onRightClick(@Nonnull PlayerInteractEvent event) {
		if (event.getClickedBlock() == null) return;
		Block targetBlock = BlockUtils.getFlintAndSteelTargetBlock(event.getClickedBlock(), event.getBlockFace());
		summonLightning(targetBlock);

		if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
			PlayerInventoryUtils.consumeDurability(event, 4);
		}
	}

	private void summonLightning(@Nonnull Block block) {
		fusedLightningShard.spawnShards = false;
		block.getWorld().strikeLightning(BlockUtils.getLightningTarget(block).getLocation().add(0.5, 1, 0.5));
		fusedLightningShard.spawnShards = true;
	}

	@Nonnull
	@Override
	public String getKey() {
		return "lightning_steel";
	}

	@Nonnull
	@Override
	public ItemStack createItemStack() {
		return ItemBuilder.createItem(Material.FLINT_AND_STEEL, "§bLightning Steel");
	}

	@Override
	public int getCustomModelData() {
		return 124;
	}

	@Override
	protected void createRecipes(@Nonnull RecipeInfoContainer container) {
		container.addRecipe(new ShapelessRecipeInfo().addIngredients(Material.IRON_INGOT).
				addIngredients(lightningShard.getItemStack()));
	}

	@Override
	public boolean onDispenserInteract(@Nonnull BlockDispenseEvent event) {
		Bukkit.getScheduler().runTask(CustomItems.getInstance(), () -> {
			Directional directional = (Directional) event.getBlock().getBlockData();
			summonLightning(event.getBlock().getRelative(directional.getFacing()));
			DispenserInventoryUtils.consumeItemDurability(event.getBlock(), event.getItem(), 4);
		});
		return false;
	}

	@Override
	public void onItemBurn(@Nonnull Item item) {
		super.onItemBurn(item);
		ItemStack itemStack = new ItemStack(Material.IRON_NUGGET);
		itemStack.setAmount(ThreadLocalRandom.current().nextInt(5));
		Item dropItem = item.getWorld().dropItem(item.getLocation(), itemStack);
		dropItem.setInvulnerable(true);
	}

}
    