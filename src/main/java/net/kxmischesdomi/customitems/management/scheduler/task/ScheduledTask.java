package net.kxmischesdomi.customitems.management.scheduler.task;

import javax.annotation.Nonnegative;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 2.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ScheduledTask {

	@Nonnegative
	int ticks();

	boolean async() default true;

}
