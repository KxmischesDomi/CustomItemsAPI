package net.kxmischesdomi.customitems.test;

import net.kxmischesdomi.customitems.CustomItems;
import net.kxmischesdomi.customitems.tab.CustomTab;
import net.kxmischesdomi.customitems.tab.ICustomTab;
import net.kxmischesdomi.customitems.test.items.*;
import net.kxmischesdomi.customitems.test.items.emerald.EmeraldArmorPiece;
import net.kxmischesdomi.customitems.test.items.game.MineSweeperGameConsoleItem;
import net.kxmischesdomi.customitems.test.items.health.GreenHealthContainer;
import net.kxmischesdomi.customitems.test.items.health.RedHealthContainer;
import net.kxmischesdomi.customitems.test.items.health.YellowHealthContainer;
import net.kxmischesdomi.customitems.utils.bukkit.item.ItemBuilder;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfo.ShapedRecipeInfo;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CustomItemsLoader {

	public static void load() {
		CustomItems customItems = CustomItems.getInstance();

		CustomTab customTab = new CustomTab(ItemBuilder.createItem(Material.EMERALD, "§2Test"));
		customItems.getCustomItemsManagement().registerCustomTab(customTab);

		customTab.registerCustomItem(new FusedLightningShardItem());
		customTab.registerCustomItem(new LightningShardItem());
		customTab.registerCustomItem(new LightningSteelItem());

		customTab.registerCustomItem(new MobSpawnEggItem("charged_creeper_spawn_egg", "Charged Creeper Spawn Egg", Material.CREEPER_SPAWN_EGG, (world, location) -> {
			Creeper creeper = (Creeper) world.spawnEntity(location, EntityType.CREEPER);
			creeper.setPowered(true);
			return creeper;
		}));
		customTab.registerCustomItem(new MobSpawnEggItem("ender_dragon_spawn_egg", "Ender Dragon Spawn Egg", Material.ENDERMAN_SPAWN_EGG, (world, location) -> world.spawnEntity(location, EntityType.ENDER_DRAGON)));
		customTab.registerCustomItem(new MobSpawnEggItem("wither_spawn_egg", "Wither Spawn Egg", Material.WITHER_SKELETON_SPAWN_EGG, (world, location) -> world.spawnEntity(location, EntityType.WITHER)));

		customTab.registerCustomItem(new BandagesItem());
		customTab.registerCustomItem(new BurgerItem());

		customTab.registerCustomItem(new SuicideStick());
		customTab.registerCustomItem(new TimeCrystalItem());

		customTab.registerCustomItem(new RedHealthContainer());
		customTab.registerCustomItem(new YellowHealthContainer());
		customTab.registerCustomItem(new GreenHealthContainer());

		customTab.registerCustomItem(new MineSweeperGameConsoleItem());

		loadEmeraldSet(customTab);
	}

	private static void loadEmeraldSet(@Nonnull ICustomTab tab) {
		tab.registerCustomItem(new EmeraldArmorPiece("helmet", Material.DIAMOND_HELMET, "Emerald Helmet", EquipmentSlot.HEAD, 2, 1, new ShapedRecipeInfo().setShape("xxx", "x x").setIngredient('x', Material.EMERALD)));
		tab.registerCustomItem(new EmeraldArmorPiece("chestplate", Material.DIAMOND_CHESTPLATE, "Emerald Chestplate", EquipmentSlot.CHEST, 7, 1, new ShapedRecipeInfo().setShape("x x", "xxx", "xxx").setIngredient('x', Material.EMERALD)));
		tab.registerCustomItem(new EmeraldArmorPiece("leggings", Material.DIAMOND_LEGGINGS, "Emerald Leggings", EquipmentSlot.LEGS, 5, 1, new ShapedRecipeInfo().setShape("xxx", "x x", "x x").setIngredient('x', Material.EMERALD)));
		tab.registerCustomItem(new EmeraldArmorPiece("boots", Material.DIAMOND_BOOTS, "Emerald Boots", EquipmentSlot.FEET, 3, 2, new ShapedRecipeInfo().setShape("x x", "x x").setIngredient('x', Material.EMERALD)));
	}

}
    