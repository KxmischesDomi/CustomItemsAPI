package net.kxmischesdomi.customitems.utils.bukkit.inventory;

import net.kxmischesdomi.customitems.utils.bukkit.customitems.CustomItemUtils;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class PlayerInventoryUtils {

	public static void consumeItem(@Nonnull PlayerInteractEvent event) {
		consumeItem(event.getPlayer().getInventory(), event.getItem());
	}

	public static void consumeItem(@Nonnull PlayerInventory inventory, @Nullable ItemStack itemStack) {
		if (itemStack == null) return;
		ItemStack newItemStack = itemStack.clone();
		newItemStack.setAmount(newItemStack.getAmount() - 1);
		replaceItemInHand(inventory, itemStack, newItemStack);
	}

	public static void consumeDurability(@Nonnull PlayerInteractEvent event) {
		consumeDurability(event, 1);
	}

	public static void consumeDurability(@Nonnull PlayerInteractEvent event, int consumedDurability) {
		if (event.getHand() == null) return;
		ItemStack itemInHand = event.getItem();
		if (itemInHand == null) return;
		if (!(itemInHand.getItemMeta() instanceof Damageable)) return;
		Damageable meta = (Damageable) itemInHand.getItemMeta();
		meta.setDamage(meta.getDamage() + consumedDurability);
		if (meta.getDamage() >= itemInHand.getType().getMaxDurability()) {
			event.getPlayer().getInventory().setItem(getHandSlot(event.getPlayer().getInventory(), event.getHand()), null);
			return;
		}
		itemInHand.setItemMeta((ItemMeta) meta);
	}

	public static int getHandSlot(@Nonnull PlayerInventory inventory, @Nonnull EquipmentSlot equipmentSlot) {
		return equipmentSlot == EquipmentSlot.HAND ? inventory.getHeldItemSlot() : equipmentSlot == EquipmentSlot.OFF_HAND ? 45 : -1;
	}

	public static void replaceItemInHand(@Nonnull PlayerInventory inventory, @Nullable ItemStack oldItemStack, @Nullable ItemStack newItemStack) {
		if (oldItemStack == null) return;
		if (CustomItemUtils.itemEquals(inventory.getItemInMainHand(), oldItemStack)) {
			inventory.setItemInMainHand(newItemStack);
			return;
		}
		if (CustomItemUtils.itemEquals(inventory.getItemInOffHand(), oldItemStack)) {
			inventory.setItemInOffHand(newItemStack);
		}
	}

	public static int getHeldSlotOfItem(@Nonnull PlayerInventory inventory, @Nullable ItemStack itemStack) {
		if (itemStack == null) return -1;
		if (inventory.getItemInMainHand().equals(itemStack)) return inventory.getHeldItemSlot();
		if (inventory.getItemInOffHand().equals(itemStack)) return 45;
		return -1;
	}

}
    