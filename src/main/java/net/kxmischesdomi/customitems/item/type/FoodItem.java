package net.kxmischesdomi.customitems.item.type;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public abstract class FoodItem extends ConsumableItem {

	@Override
	public boolean onConsume(@Nonnull PlayerItemConsumeEvent event) {
		addFoodLevels(event.getPlayer(), getFoodLevels());
		addSaturation(event.getPlayer(), getSaturation());
		return true;
	}

	protected void addFoodLevels(@Nonnull Player player, int foodLevelToAdd) {
		int maxFoodLevel = 20;
		int newFoodLevel = player.getFoodLevel() + foodLevelToAdd;
		if (newFoodLevel >= maxFoodLevel) newFoodLevel = maxFoodLevel;
		player.setFoodLevel(newFoodLevel);
	}

	protected void addSaturation(@Nonnull Player player, int saturationToAdd) {
		int maxSaturation = 20;
		int newSaturation = player.getFoodLevel() + saturationToAdd;
		if (newSaturation >= maxSaturation) newSaturation = maxSaturation;
		player.setSaturation(newSaturation);
	}

	public abstract int getFoodLevels();
	public abstract int getSaturation();

}
    