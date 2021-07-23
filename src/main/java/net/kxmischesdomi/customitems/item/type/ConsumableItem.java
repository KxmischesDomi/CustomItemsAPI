package net.kxmischesdomi.customitems.item.type;

import net.kxmischesdomi.customitems.item.AbstractCustomItem;
import net.kxmischesdomi.customitems.item.attribute.IAttribute;
import net.kxmischesdomi.customitems.utils.bukkit.inventory.PlayerInventoryUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public abstract class ConsumableItem extends AbstractCustomItem {

	public ConsumableItem(IAttribute... attributes) {
		super(attributes);
	}

	@EventHandler
	public void defaultOnConsume(@Nonnull PlayerItemConsumeEvent event) {
		if (!isCurrentItem(event.getItem())) return;
		event.setCancelled(true);
		if (onConsume(event)) {
			PlayerInventoryUtils.consumeItem(event.getPlayer().getInventory(), event.getItem());
		}


	}

	/***
	 * @return if the item was consumed
	 */
	public abstract boolean onConsume(@Nonnull PlayerItemConsumeEvent event);

}
