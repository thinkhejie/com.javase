package com.java.aop;

import j.se.aop.HelloImp;
import j.se.aop.IHello;
import j.se.aop.LogHandler;

public class ProxyDemo
{
	public static void main(String[] args) throws SecurityException, NoSuchMethodException
	{
		LogHandler logHandler = new LogHandler();
		IHello hello = (IHello) logHandler.bind(new HelloImp());
		hello.toHello("hej");
	}
}
