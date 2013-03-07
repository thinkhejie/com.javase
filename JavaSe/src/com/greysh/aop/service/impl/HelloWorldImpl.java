package com.greysh.aop.service.impl;

import com.greysh.aop.service.HelloWorld;

public class HelloWorldImpl implements HelloWorld {
	@Override
	public void say() {
		System.out.println("Say HelloWorld");
	}

}
