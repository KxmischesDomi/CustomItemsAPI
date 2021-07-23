package net.kxmischesdomi.customitems.management.scheduler;

import net.kxmischesdomi.customitems.CustomItems;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 2.0
 *
 * @see ScheduleManager
 */
final class ScheduledTaskExecutor extends AbstractTaskExecutor {

	private final ScheduledTaskConfig config;
	private BukkitTask task;

	ScheduledTaskExecutor(@Nonnull ScheduledTaskConfig config) {
		this.config = config;
	}

	public void stop() {
		if (task != null && !task.isCancelled()) {
			task.cancel();
			task = null;
		}
	}

	public void start() {
		BukkitScheduler scheduler = Bukkit.getScheduler();
		CustomItems plugin = CustomItems.getInstance();
		task = config.isAsync() ? scheduler.runTaskTimerAsynchronously(plugin, this, 0, config.getRate()) :
								  scheduler.runTaskTimer(plugin, this, 0, config.getRate());
	}

	@Nonnull
	@Override
	public ScheduledTaskConfig getConfig() {
		return config;
	}

}
