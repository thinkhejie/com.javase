package com.greysh.aop.test;

import com.greysh.aop.factory.ProxyFactory;
import com.greysh.aop.service.HelloWorld;
import com.greysh.aop.service.impl.HelloWorldImpl;

public class TestHelloWorld {

	public static void main(String[] args) {
		HelloWorld mb = new HelloWorldImpl();
		HelloWorld bi = (HelloWorld) ProxyFactory.getProxy(mb);
		bi.say();

		ProxyFactory.getBean(HelloWorld.class);
	}
}
