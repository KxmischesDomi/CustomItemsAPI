package net.kxmischesdomi.customitems.management.scheduler;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 2.0
 */
public abstract class AbstractTaskExecutor implements Runnable {

	protected final List<ScheduledFunction> functions = new ArrayList<>(1);

	@Override
	public void run() {
		for (ScheduledFunction function : functions) {
			try {
				function.invoke();
			} catch (InvocationTargetException | IllegalAccessException ex) {
				System.out.println("An exception occurred while executing " + function + " :: " + ex);
			}
		}
	}

	@Nonnull
	public abstract AbstractTaskConfig getConfig();

	public void register(@Nonnull ScheduledFunction function) {
		functions.add(function);
	}

	public void unregister(@Nonnull Object holder) {
		functions.removeIf(function -> function.getHolder() == holder);
	}

}
