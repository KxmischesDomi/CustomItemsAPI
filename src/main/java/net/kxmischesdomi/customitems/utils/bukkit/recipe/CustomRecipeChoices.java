package net.kxmischesdomi.customitems.utils.bukkit.recipe;

import org.bukkit.Material;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CustomRecipeChoices {

	public static class WoolChoice extends MaterialChoice {

		public WoolChoice() {
			super(Material.WHITE_WOOL, Material.ORANGE_WOOL, Material.MAGENTA_WOOL, Material.LIGHT_BLUE_WOOL,
					Material.YELLOW_WOOL, Material.LIME_WOOL, Material.PINK_WOOL, Material.GRAY_WOOL,
					Material.LIGHT_GRAY_WOOL, Material.CYAN_WOOL, Material.PURPLE_WOOL, Material.BLUE_WOOL,
					Material.BROWN_WOOL, Material.GREEN_WOOL, Material.RED_WOOL, Material.BLACK_WOOL
			);
		}

	}

}
    