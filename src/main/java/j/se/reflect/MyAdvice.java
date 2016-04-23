package j.se.reflect;

import java.lang.reflect.Method;

public class MyAdvice implements Advice {
	long beginTime = 0;

	@Override
	public void afterMethod(Method method) {
		// TODO Auto-generated method stub
		System.out.println("�Ӵ��ǲ��ͱ�ҵ�ϰ�����");
		long endTime = System.currentTimeMillis();
		System.out.println(method.getName() + " running time of " + (endTime - beginTime));

	}

	@Override
	public void beforeMethod(Method method) {
		// TODO Auto-generated method stub
		System.out.println("�����ǲ�����ѧϰ����");
		beginTime = System.currentTimeMillis();
	}

}
