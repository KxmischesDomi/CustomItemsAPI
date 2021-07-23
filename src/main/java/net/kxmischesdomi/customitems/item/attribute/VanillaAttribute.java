package net.kxmischesdomi.customitems.item.attribute;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class VanillaAttribute implements IAttribute {

	private final Attribute attribute;
	private final List<AttributeModifier> modifiers = new LinkedList<>();

	public VanillaAttribute(Attribute attribute, Supplier<Double> amountSupplier) {
		this.attribute = attribute;
		this.modifiers.add(new AttributeModifier(UUID.randomUUID(), attribute.name().toLowerCase(), amountSupplier.get(), Operation.ADD_NUMBER));
	}

	public VanillaAttribute(Attribute attribute, Supplier<Double> amountSupplier, Operation operation) {
		this.attribute = attribute;
		this.modifiers.add(new AttributeModifier(UUID.randomUUID(), attribute.name().toLowerCase(), amountSupplier.get(), operation));
	}

	public VanillaAttribute(Attribute attribute, Supplier<Double> amountSupplier, EquipmentSlot... equipmentSlot) {
		this.attribute = attribute;
		for (EquipmentSlot slot : equipmentSlot) {
			this.modifiers.add(new AttributeModifier(UUID.randomUUID(), attribute.name().toLowerCase(), amountSupplier.get(), Operation.ADD_NUMBER, slot));
		}
	}

	public VanillaAttribute(Attribute attribute, Supplier<Double> amountSupplier, Operation operation, EquipmentSlot... equipmentSlot) {
		this.attribute = attribute;
		for (EquipmentSlot slot : equipmentSlot) {
			this.modifiers.add(new AttributeModifier(UUID.randomUUID(), attribute.name().toLowerCase(), amountSupplier.get(), operation, slot));
		}
	}

	public VanillaAttribute(Attribute attribute, double amount) {
		this.attribute = attribute;
		this.modifiers.add(new AttributeModifier(UUID.randomUUID(), attribute.name().toLowerCase(), amount, Operation.ADD_NUMBER));
	}

	public VanillaAttribute(Attribute attribute, double amount, Operation operation) {
		this.attribute = attribute;
		this.modifiers.add(new AttributeModifier(UUID.randomUUID(), attribute.name().toLowerCase(), amount, operation));
	}

	public VanillaAttribute(Attribute attribute, double amount, EquipmentSlot... equipmentSlot) {
		this.attribute = attribute;
		for (EquipmentSlot slot : equipmentSlot) {
			this.modifiers.add(new AttributeModifier(UUID.randomUUID(), attribute.name().toLowerCase(), amount, Operation.ADD_NUMBER, slot));
		}
	}

	public VanillaAttribute(Attribute attribute, double amount, Operation operation, EquipmentSlot... equipmentSlot) {
		this.attribute = attribute;
		for (EquipmentSlot slot : equipmentSlot) {
			this.modifiers.add(new AttributeModifier(UUID.randomUUID(), attribute.name().toLowerCase(), amount, operation, slot));
		}
	}

	@Override
	public void apply(@Nonnull ItemStack itemStack) {
		ItemMeta meta = itemStack.getItemMeta();
		if (meta == null) return;
		for (AttributeModifier modifier : modifiers) {
			if (modifier.getAmount() == 0) return;
			meta.addAttributeModifier(attribute, modifier);
		}
		itemStack.setItemMeta(meta);
	}

}
    