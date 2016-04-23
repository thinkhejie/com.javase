package j.se.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationHandlerImpl implements InvocationHandler {
	/*
	 * (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
	 * java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// method.invoke(obj, args);
		return null;
	}
}
