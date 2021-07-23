package net.kxmischesdomi.customitems.test.items;

import net.kxmischesdomi.customitems.item.AbstractCustomItem;
import net.kxmischesdomi.customitems.item.attribute.VanillaAttribute;
import net.kxmischesdomi.customitems.utils.bukkit.item.ItemBuilder;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfoContainer;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class TimeCrystalItem extends AbstractCustomItem {

	public TimeCrystalItem() {
		super(
				new VanillaAttribute(Attribute.GENERIC_ATTACK_SPEED, 3, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND, EquipmentSlot.OFF_HAND),
				new VanillaAttribute(Attribute.GENERIC_MOVEMENT_SPEED, 3, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND, EquipmentSlot.OFF_HAND),
				new VanillaAttribute(Attribute.GENERIC_FLYING_SPEED, 3, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND, EquipmentSlot.OFF_HAND)
		);
	}

	@Nonnull
	@Override
	protected ItemStack createItemStack() {
		return ItemBuilder.createItem(Material.PRISMARINE_CRYSTALS, "§bTime Crystal");
	}

	@Override
	public int getCustomModelData() {
		return 123;
	}

	@Override
	protected void createRecipes(@Nonnull RecipeInfoContainer container) {

	}

	@Nonnull
	@Override
	public String getKey() {
		return "time_crystal";
	}

}
    