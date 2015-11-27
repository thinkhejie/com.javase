package com.greysh.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
	private Object target;

	public void setTarget(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("Before Helloworld");
		Object result = method.invoke(target, args);
		System.out.println(result);
		System.out.println("Finish Helloworld");
		return null;
	}
}
