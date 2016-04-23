package com.greysh.aop.factory;

import java.lang.reflect.Proxy;

import com.greysh.aop.proxy.ProxyHandler;

public class ProxyFactory {

	public static Object getProxy(Object obj) {
		ProxyHandler bn = new ProxyHandler();
		bn.setTarget(obj);
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), bn);
	}

	public static <T> T getBean(Class<T> obj) {
		ProxyHandler bn = new ProxyHandler();
		try {
			obj.isInterface();
			Class t = obj.asSubclass(obj);
			System.out.println(t.getName());
			bn.setTarget(obj.newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), bn);
	}
}
