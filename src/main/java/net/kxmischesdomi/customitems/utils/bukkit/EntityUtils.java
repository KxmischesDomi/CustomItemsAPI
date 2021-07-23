package net.kxmischesdomi.customitems.utils.bukkit;

import org.bukkit.entity.EntityType;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class EntityUtils {

	public static boolean isBoos(@Nonnull EntityType entityType) {
		return entityType == EntityType.ENDER_DRAGON || entityType == EntityType.WITHER || entityType == EntityType.ELDER_GUARDIAN || entityType.name().contains("WARDEN");
	}

	public static boolean isNetherMob(@Nonnull EntityType entityType) {
		if (entityType == EntityType.GHAST || entityType == EntityType.WITHER_SKELETON || entityType == EntityType.BLAZE) return true;
		String name = entityType.name();
		return name.contains("PIGMEN") || name.contains("PIGLIN") || name.contains("PIG_ZOMBIE");
	}

	public static boolean isEndMob(@Nonnull EntityType entityType) {
		return entityType == EntityType.ENDERMAN || entityType == EntityType.ENDERMITE || entityType == EntityType.SHULKER;
	}

}
    