package net.kxmischesdomi.customitems.item.type;

import net.kxmischesdomi.customitems.item.AbstractCustomItem;
import net.kxmischesdomi.customitems.item.attribute.IAttribute;
import net.kxmischesdomi.customitems.item.attribute.ResetVanillaAttribute;
import net.kxmischesdomi.customitems.item.attribute.VanillaAttribute;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.inventory.EquipmentSlot;

/**
 * Represents a custom piece of an armor and provides simple ways of modifying it.
 *
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public abstract class ArmorPieceItem extends AbstractCustomItem {

	public ArmorPieceItem(EquipmentSlot[] equipmentSlots, IAttribute... attributes) {
		this(false, equipmentSlots, attributes);
	}

	public ArmorPieceItem(boolean keepOld, EquipmentSlot[] equipmentSlots, IAttribute... attributes) {
		super(attributes);
		if (!keepOld) addAttributes(new ResetVanillaAttribute());
		addAttributes(
				new VanillaAttribute(Attribute.GENERIC_ARMOR, this::getArmor, Operation.ADD_NUMBER, equipmentSlots),
				new VanillaAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS, this::getToughness, Operation.ADD_NUMBER, equipmentSlots)
		);
	}

	public abstract double getArmor();
	public abstract double getToughness();

}
    