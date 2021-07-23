package net.kxmischesdomi.customitems.test.items.emerald;

import net.kxmischesdomi.customitems.item.attribute.IAttribute;
import net.kxmischesdomi.customitems.item.type.ArmorPieceItem;
import net.kxmischesdomi.customitems.utils.bukkit.item.ItemBuilder;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfo;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfoContainer;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class EmeraldArmorPiece extends ArmorPieceItem {

	private final String key;
	private final Material material;
	private final String name;
	private final int armor, toughness;
	private final RecipeInfo recipeInfo;

	public EmeraldArmorPiece(String key, Material material, String name, EquipmentSlot equipmentSlot, int armor, int toughness, RecipeInfo recipeInfo, IAttribute... attributes) {
		super(new EquipmentSlot[] {equipmentSlot}, attributes);
		this.key = "emerald_" + key;
		this.material = material;
		this.name = name;
		this.armor = armor;
		this.toughness = toughness;
		this.recipeInfo = recipeInfo;
	}

	@Nonnull
	@Override
	protected ItemStack createItemStack() {
		return ItemBuilder.createItem(material, name);
	}

	@Override
	public int getCustomModelData() {
		return 6666;
	}

	@Override
	protected void createRecipes(@Nonnull RecipeInfoContainer container) {
		container.addRecipe(recipeInfo);
	}

	@Nonnull
	@Override
	public String getKey() {
		return key;
	}

	@Override
	public double getArmor() {
		return armor;
	}

	@Override
	public double getToughness() {
		return toughness;
	}

}
    