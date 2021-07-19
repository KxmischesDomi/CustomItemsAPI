package net.kxmischesdomi.customitems;

import net.kxmischesdomi.customitems.api.management.CustomItemsManagement;
import net.kxmischesdomi.customitems.spigot.commands.CustomItemsCommand;
import net.kxmischesdomi.customitems.spigot.listener.CraftingListener;
import net.kxmischesdomi.customitems.spigot.listener.CustomEventsListener;
import net.kxmischesdomi.customitems.spigot.listener.MenuListener;
import net.kxmischesdomi.customitems.test.Test;
import net.kxmischesdomi.customitems.utils.plugin.BukkitModule;
import net.kxmischesdomi.customitems.utils.recipe.CustomItemRecipe;
import net.kxmischesdomi.customitems.utils.recipe.RecipeBuilder;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CustomItems extends BukkitModule {

	private static CustomItems instance;

	private CustomItemsManagement customItemsManagement;

	private boolean inventoriesGenerated;

	@Override
	public void onLoad() {
		instance = this;

		RecipeBuilder.setDefaultPlugin(this);
		CustomItemRecipe.setDefaultPlugin(this);

		customItemsManagement = new CustomItemsManagement(this);

		Test.test();
	}

	@Override
	public void onEnable() {
		super.onEnable();

		registerPluginListener();
		registerPluginCommands();

		inventoriesGenerated = true;
		customItemsManagement.generateTabInventories();
	}

	public void registerPluginCommands() {
		try {
			getCommand("customitems").setExecutor(new CustomItemsCommand());
		} catch (Exception ex) { ex.printStackTrace(); }
	}

	public void registerPluginListener() {
		registerListener(
				new CustomEventsListener(),
				new MenuListener(),
				new CraftingListener()
		);
	}

	public CustomItemsManagement getCustomItemsManagement() {
		return customItemsManagement;
	}

	public boolean inventoriesGenerated() {
		return inventoriesGenerated;
	}

	public static CustomItems getInstance() {
		return instance;
	}

}
    