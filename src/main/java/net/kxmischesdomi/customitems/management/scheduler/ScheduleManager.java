package net.kxmischesdomi.customitems.management.scheduler;

import net.kxmischesdomi.customitems.management.scheduler.task.ScheduledTask;
import net.kxmischesdomi.customitems.utils.misc.ReflectionUtils;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 2.0
 */
public final class ScheduleManager {

	private final Map<ScheduledTaskConfig, ScheduledTaskExecutor> scheduledTaskExecutorsByConfig = new ConcurrentHashMap<>();
	private boolean started = false;

	public void register(@Nonnull Object... schedulers) {
		for (Object scheduler : schedulers) {
			register(scheduler);
		}
	}

	public void register(@Nonnull Object scheduler) {
		for (Method method : ReflectionUtils.getMethodsAnnotatedWith(scheduler.getClass(), ScheduledTask.class)) {
			if (method.getParameterCount() != 0) {
				System.out.println("Could not register scheduler " + method);
				continue;
			}

			ScheduledTask annotation = method.getAnnotation(ScheduledTask.class);
			ScheduledFunction function = new ScheduledFunction(scheduler, method);

			System.out.println("Registered scheduled task " + function);
			register(function, new ScheduledTaskConfig(annotation));
		}
	}

	public void unregister(@Nonnull Object object) {
		for (ScheduledTaskExecutor scheduler : scheduledTaskExecutorsByConfig.values()) {
			scheduler.unregister(object);
		}
	}

	private void register(@Nonnull ScheduledFunction function, @Nonnull AbstractTaskConfig config) {
		if (config instanceof ScheduledTaskConfig) {
			ScheduledTaskConfig taskConfig = (ScheduledTaskConfig) config;
			if (taskConfig.getRate() < 1) {
				System.out.println("Schedule rate cannot be less than 1; Could not register " + function);
				return;
			}

			ScheduledTaskExecutor executor = getOrCreateScheduledTaskExecutor(taskConfig);
			executor.register(function);
		}
	}

	@Nonnull
	private ScheduledTaskExecutor getOrCreateScheduledTaskExecutor(@Nonnull ScheduledTaskConfig config) {
		ScheduledTaskExecutor executor = scheduledTaskExecutorsByConfig.get(config);
		if (executor != null) return executor;

		// Create new task
		executor = new ScheduledTaskExecutor(config);
		if (started) executor.start();
		scheduledTaskExecutorsByConfig.put(config, executor);
		return executor;
	}

	public void stop() {
		started = false;
		scheduledTaskExecutorsByConfig.values().forEach(ScheduledTaskExecutor::stop);
		scheduledTaskExecutorsByConfig.clear();
	}

	public void start() {
		started = true;
		scheduledTaskExecutorsByConfig.values().forEach(ScheduledTaskExecutor::start);
	}

}
