package net.kxmischesdomi.customitems.item.type;

import net.kxmischesdomi.customitems.item.attribute.IAttribute;
import net.kxmischesdomi.customitems.utils.bukkit.BlockUtils;
import net.kxmischesdomi.customitems.utils.bukkit.inventory.PlayerInventoryUtils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public abstract class CustomItemSpawnEgg extends RightInteractableCustomItem {

	public CustomItemSpawnEgg(IAttribute... attributes) {
		super(attributes);
	}

	@Override
	public void onRightClick(@Nonnull PlayerInteractEvent event) {
		if (event.getPlayer().getGameMode() == GameMode.ADVENTURE) return;
		if (event.getClickedBlock() == null) return;

		// Get the target block where the entity will be spawned in
		Block blockToSpawnIn = BlockUtils.getSpawnEggTargetBlock(event.getClickedBlock(), event.getBlockFace());

		Entity entitySpawned = spawnEntity(blockToSpawnIn.getWorld(), blockToSpawnIn.getLocation().add(0.5, 0, 0.5));

		// TODO: CHECK IF ENTITY CAN BE SPAWNED AT PEACEFUL

		if (entitySpawned != null && event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
			PlayerInventoryUtils.consumeItem(event);
		}
	}

	/**
	 * @param location the location where the entity should be spawned.
	 * @param world the world to spawn the entity in. Given to prevent NullPointerExceptions when getting the world with the location
	 * @return the entity that was spawned
	 */
	public abstract Entity spawnEntity(@Nonnull World world, @Nonnull Location location);

}
    