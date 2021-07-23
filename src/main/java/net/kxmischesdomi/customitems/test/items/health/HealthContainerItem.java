package net.kxmischesdomi.customitems.test.items.health;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import net.kxmischesdomi.customitems.item.type.RightInteractableCustomItem;
import net.kxmischesdomi.customitems.utils.bukkit.ChatUtils;
import net.kxmischesdomi.customitems.utils.bukkit.inventory.PlayerInventoryUtils;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public abstract class HealthContainerItem extends RightInteractableCustomItem {

	@Override
	public void onRightClick(@Nonnull PlayerInteractEvent event) {
		Player player = event.getPlayer();
		int containerUses = getContainerUses(player);
		if (containerUses > getMaxContainer()) {
			setContainerUses(player, getMaxContainer());
			return;
		} else if (containerUses == getMaxContainer()) return;
		if (addContainerUses(player, 1)) {
			PlayerInventoryUtils.consumeItem(event);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_BURP, 0.7f, 3f);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerDeath(@Nonnull PlayerDeathEvent event) {

		int containerUses = getContainerUses(event.getEntity());
		removeContainerUses(event.getEntity(), containerUses);

		int containerLost = randomContainerLost(containerUses);

		if (containerUses != 0) {
			AttributeInstance attribute = event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH);
			if (attribute != null) attribute.setBaseValue(20);
		}

		if (containerLost != 0) {
			boolean multiple = containerLost != 1;
			event.getEntity().sendMessage(containerLost + " " + ChatUtils.getWithoutColorCodes(getDisplayName()) + (multiple ? "s" : "") + " " + (multiple ? "we're" : "was") + " destroyed");
		}

		for (int i = 0; i < containerUses - containerLost; i++) {
			event.getDrops().add(getItemStack());
		}

	}

	private int randomContainerLost(@Nonnegative int used) {

		int lost = 0;
		int percent = 0;
		for (int i = 0; i < used; i++) {
			percent += ThreadLocalRandom.current().nextInt(3);
			if (trueAtPercent(percent)) lost++;
		}

		return lost;
	}


	@EventHandler(priority = EventPriority.LOW)
	public void onEntityDeath(@Nonnull EntityDeathEvent event) {
		if (spawnItem(event)) {
			event.getDrops().add(getItemStack());
		}
	}

	public boolean addContainerUses(@Nonnull Player player, @Nonnegative int uses) {
		setContainerUses(player, getContainerUses(player) + uses);

		AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
		if (attribute == null) return false;
		for (int i = 0; i < uses; i++) {
			attribute.setBaseValue(attribute.getBaseValue() + getHealthPerContainer());
		}
		return true;
	}

	public void removeContainerUses(@Nonnull Player player, @Nonnegative int uses) {
		setContainerUses(player, getContainerUses(player) - uses);

		AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
		if (attribute == null) return;
		for (int i = 0; i < uses; i++) {
			attribute.setBaseValue(attribute.getBaseValue() - getHealthPerContainer());
		}
	}

	public void setContainerUses(@Nonnull Player player, @Nonnegative int uses) {
		getPlayerCompound(player).setInteger(getKey(), uses);
	}

	public int getContainerUses(@Nonnull Player player) {
		int uses = getPlayerCompound(player).getInteger(getKey());
		return Math.min(uses, getMaxContainer());
	}

	public NBTCompound getPlayerCompound(@Nonnull Player player) {
		return new NBTEntity(player).getPersistentDataContainer();
	}

	protected boolean trueAtPercent(int percent) {
		int randomInt = ThreadLocalRandom.current().nextInt(100) + 1;
		return randomInt <= percent;
	}

	public abstract boolean spawnItem(@Nonnull EntityDeathEvent event);
	public abstract int getHealthPerContainer();
	public abstract int getMaxContainer();

}
    