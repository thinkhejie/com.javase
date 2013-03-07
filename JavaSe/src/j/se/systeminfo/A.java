package j.se.systeminfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class A implements InvocationHandler {
	Object obj = null;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		method.invoke(obj, args);
		return null;
	}
}