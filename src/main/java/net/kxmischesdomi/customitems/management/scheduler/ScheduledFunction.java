package net.kxmischesdomi.customitems.management.scheduler;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 2.0
 */
public final class ScheduledFunction {

	private final Method method;
	private final Object holder;

	ScheduledFunction(@Nonnull Object holder, @Nonnull Method method) {
		this.method = method;
		this.holder = holder;
	}

	public void invoke() throws InvocationTargetException, IllegalAccessException {
		method.setAccessible(true);
		method.invoke(holder);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ScheduledFunction function = (ScheduledFunction) o;
		return method.equals(function.method) && holder.equals(function.holder);
	}

	@Override
	public int hashCode() {
		return Objects.hash(method, holder);
	}

	@Nonnull
	public Object getHolder() {
		return holder;
	}

	@Override
	public String toString() {
		return holder.getClass().getName() + "." + method.getName() + "()";
	}

}
