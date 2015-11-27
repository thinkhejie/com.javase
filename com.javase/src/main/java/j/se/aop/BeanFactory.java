package j.se.aop;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;

public class BeanFactory implements InvocationHandler {
	Properties props = new Properties();

	public BeanFactory(InputStream ips) {
		try {
			props.load(ips);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object getBean(String name) {
		String className = props.getProperty(name);
		Object bean = null;
		Object tt = null;
		try {
			Class clazz = Class.forName(className);
			bean = clazz.newInstance();
			tt = Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), this);
			// if (bean instanceof ProxyFactoryBean)
			// {
			// Object proxy = null;
			// ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean) bean;
			// Advice advice = (Advice) Class.forName(props.getProperty(name +
			// ".advice")).newInstance();
			// Object target = Class.forName(props.getProperty(name +
			// ".target")).newInstance();
			// proxyFactoryBean.setAdvice(advice);
			// proxyFactoryBean.setTarget(target);
			// proxy = proxyFactoryBean.getProxy();
			// return proxy;
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tt;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
	 * java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// Object result = method.invoke(deledate, args);
		return null;
	}
}
