package net.kxmischesdomi.customitems.utils.bukkit;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class BlockUtils {

	public static Block getSpawnEggTargetBlock(@Nonnull Block block, @Nonnull BlockFace blockFace) {
		if (!block.isPassable()) {
			block = block.getRelative(blockFace);
		}
		return block;
	}

	public static Block getFlintAndSteelTargetBlock(@Nonnull Block block, @Nonnull BlockFace blockFace) {
		return block.getRelative(blockFace);
	}

	public static Block getLightningTarget(@Nonnull Block block) {
		return block.getWorld().getHighestBlockAt(block.getLocation());
	}

}
