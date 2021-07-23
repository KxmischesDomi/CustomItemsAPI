package net.kxmischesdomi.customitems.item.type;

import net.kxmischesdomi.customitems.item.attribute.IAttribute;
import org.bukkit.event.Event.Result;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public abstract class RightInteractableCustomItem extends InteractableCustomItem {

	public RightInteractableCustomItem(IAttribute... attributes) {
		super(attributes);
	}

	@Override
	public final void onPlayerInteract(@Nonnull PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
			event.setUseItemInHand(Result.ALLOW);
			return;
		}
		onRightClick(event);
	}

	public abstract void onRightClick(@Nonnull PlayerInteractEvent event);

}
    