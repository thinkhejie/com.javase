package com.java.aop;

import j.se.aop.BeanFactory;

import java.io.InputStream;

import org.junit.Test;

public class AopFrameworkTest
{
	@Test
	public void testAop()
	{
		try
		{
			InputStream ips = ClassLoader.getSystemResourceAsStream("bean.properties");
			Object bean = new BeanFactory(ips).getBean("hello");
			System.out.println(bean.getClass().getName());
			// ((Collection) bean).clear();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
