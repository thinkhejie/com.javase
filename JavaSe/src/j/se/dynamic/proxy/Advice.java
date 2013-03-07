package j.se.dynamic.proxy;

import java.lang.reflect.Method;

public interface Advice {
	void afterMethod(Method method);

	void beforeMethod(Method method);
}
