package net.kxmischesdomi.customitems.test;

import net.kxmischesdomi.customitems.CustomItems;
import net.kxmischesdomi.customitems.api.item.InteractCustomItem;
import net.kxmischesdomi.customitems.api.tab.CustomTab;
import net.kxmischesdomi.customitems.utils.item.ItemBuilder;
import net.kxmischesdomi.customitems.utils.recipe.CustomItemRecipe;
import net.kxmischesdomi.customitems.utils.recipe.RecipeBuilder.ShapelessRecipeBuilder;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class Test {

	public static void test() {
		CustomItems customItems = CustomItems.getInstance();

		CustomTab customTab = new CustomTab(ItemBuilder.createItem(Material.ENDER_CHEST, "§5MagicCraft"));
		customItems.getCustomItemsManagement().registerCustomTab(customTab);

		customTab.registerCustomItem(new SuicideStaff());

		customTab.registerCustomItem(new InteractCustomItem() {
			@Override
			public void onInteract(@Nonnull PlayerInteractEvent event) {
				if (event.getClickedBlock() == null) return;
				event.getClickedBlock().getWorld().strikeLightning(event.getClickedBlock().getLocation());
			}

			@Nonnull
			@Override
			public String getKey() {
				return "lightning_steel";
			}

			@Nonnull
			@Override
			public ItemStack createItemStack() {
				return ItemBuilder.createItem(Material.FLINT_AND_STEEL, "§6Lightning Steel");
			}

			@Override
			public int getCustomModelData() {
				return 0;
			}

			@Override
			public CustomItemRecipe createRecipe() {
				return new CustomItemRecipe()
						.addRecipe(new ShapelessRecipeBuilder("warpbooka", getItemStack()).addIngredient(Material.FLINT, Material.BLAZE_ROD));
			}
		});

	}

}
    