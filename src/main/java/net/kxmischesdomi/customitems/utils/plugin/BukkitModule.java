package net.kxmischesdomi.customitems.utils.plugin;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class BukkitModule extends JavaPlugin {

	private final Set<Listener> listeners = new HashSet<>();

	boolean pluginEnabled = false;

	@Override
	public void onEnable() {
		pluginEnabled = true;
		registerListenersOnBukkit();
	}

	private void registerListenersOnBukkit() {
		for (Listener listener : listeners) {
			Bukkit.getPluginManager().registerEvents(listener, this);
		}
	}

	public void registerListener(@Nonnull Listener... listeners) {
		if (pluginEnabled) {
			for (Listener listener : listeners) {
				Bukkit.getPluginManager().registerEvents(listener, this);
			}
		} else {
			this.listeners.addAll(Arrays.asList(listeners));
		}
	}

}
    