package j.se.aop;

import java.lang.reflect.Method;

public interface Advice {
	void afterMethod(Method method);

	void beforeMethod(Method method);
}
