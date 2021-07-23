package net.kxmischesdomi.customitems;

import net.kxmischesdomi.customitems.bukkit.commands.CustomItemsCommand;
import net.kxmischesdomi.customitems.bukkit.listener.CraftingListener;
import net.kxmischesdomi.customitems.bukkit.listener.CustomEventsListener;
import net.kxmischesdomi.customitems.bukkit.listener.MenuListener;
import net.kxmischesdomi.customitems.management.customitems.CustomItemsManagement;
import net.kxmischesdomi.customitems.management.scheduler.ScheduleManager;
import net.kxmischesdomi.customitems.test.CustomItemsLoader;
import net.kxmischesdomi.customitems.utils.bukkit.plugin.BukkitModule;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.CustomItemRecipe;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeBuilder;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CustomItems extends BukkitModule {

	private static CustomItems instance;

	private CustomItemsManagement customItemsManagement;
	private ScheduleManager scheduleManager;

	private boolean inventoriesGenerated;

	public static CustomItems getInstance() {
		return instance;
	}

	@Override
	public void onLoad() {
		instance = this;

		RecipeBuilder.setDefaultPlugin(this);
		CustomItemRecipe.setDefaultPlugin(this);

		scheduleManager = new ScheduleManager();
		customItemsManagement = new CustomItemsManagement();

		CustomItemsLoader.load();
	}

	@Override
	public void onEnable() {
		super.onEnable();

		registerPluginListener();
		registerPluginCommands();

		inventoriesGenerated = true;
		customItemsManagement.generateTabInventories();

		scheduleManager.start();
	}

	public void registerPluginCommands() {
		try {
			getCommand("customitems").setExecutor(new CustomItemsCommand());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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

	public ScheduleManager getScheduleManager() {
		return scheduleManager;
	}

	public boolean inventoriesGenerated() {
		return inventoriesGenerated;
	}

}
    