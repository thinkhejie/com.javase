package j.se.dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author hejie
 */
public class LogHandler implements InvocationHandler {
	/**
	 * 
	 */
	private Object deledate;

	/**
	 * 获取一个委托的对象
	 * @param obj
	 * @return 委托的对象
	 */
	public Object bind(Object obj) {
		deledate = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] obj) throws Throwable {
		System.out.println(proxy.getClass().getName());
		Method[] ttt = proxy.getClass().getDeclaredMethods();
		for (Method element : ttt) {
			System.out.println(element.getName());
		}

		doBefore();

		Object result = method.invoke(deledate, obj);

		doAfter();

		return result;
	}

	/**
	 * 
	 */
	private void doBefore() {
		System.out.println("before....");
	}

	/**
	 * 
	 */
	private void doAfter() {
		System.out.println("after....");
	}
}
