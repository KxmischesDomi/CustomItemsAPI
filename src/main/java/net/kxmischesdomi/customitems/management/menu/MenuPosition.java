package net.kxmischesdomi.customitems.management.menu;

import net.kxmischesdomi.customitems.utils.bukkit.event.PlayerInventoryClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public interface MenuPosition {

	InventoryHolder HOLDER = new MenuHolder();
	Map<Player, MenuPosition> POSITIONS = new HashMap<>();

	static void set(@Nonnull Player player, @Nullable MenuPosition menuPosition) {
		POSITIONS.put(player, menuPosition);
	}

	@Nullable
	static MenuPosition get(@Nonnull Player player) {
		return POSITIONS.get(player);
	}

	void handleClick(@Nonnegative PlayerInventoryClickEvent event);

}
    