package net.kxmischesdomi.customitems.utils.bukkit.item;

import org.bukkit.Material;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class MaterialUtils {

	public static boolean isAir(@Nonnull Material material) {
		return material == Material.AIR || material == Material.CAVE_AIR || material == Material.VOID_AIR;
	}

}
    