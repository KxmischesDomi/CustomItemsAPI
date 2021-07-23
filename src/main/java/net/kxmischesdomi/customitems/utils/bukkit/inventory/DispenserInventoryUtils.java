package net.kxmischesdomi.customitems.utils.bukkit.inventory;

import net.kxmischesdomi.customitems.utils.bukkit.customitems.CustomItemUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class DispenserInventoryUtils {

	public static void consumeItemDurability(@Nonnull Block block, @Nonnull ItemStack itemStack, int consumedDurability) {
		if (!(block.getState() instanceof Dispenser)) return;
		Dispenser dispenser = (Dispenser) block.getState();

		Inventory inventory = dispenser.getInventory();
		for (int i = 0; i < inventory.getSize(); i++) {
			ItemStack item = inventory.getItem(i);
			if (CustomItemUtils.itemEquals(item, itemStack)) {
				if (item == null) continue;
				if (!(item.getItemMeta() instanceof Damageable)) continue;

				Damageable meta = (Damageable) item.getItemMeta();
				meta.setDamage(meta.getDamage() + consumedDurability);
				if (meta.getDamage() >= item.getType().getMaxDurability()) {
					inventory.setItem(i, new ItemStack(Material.AIR));
					return;
				}
				item.setItemMeta((ItemMeta) meta);
				break;
			}

		}
	}

}
    