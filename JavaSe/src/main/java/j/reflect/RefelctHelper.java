package j.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class RefelctHelper {
	/**
	 * 获取一个类的实例化对象
	 * @param 类的完全限定名
	 * @param 构造函数的参数
	 * @return 该类的实例对象
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Object getNewInstanceByClassName(String className, Object... args) throws Exception {
		Class cls = Class.forName(className);
		int mod = cls.getModifiers();

		if (Modifier.isAbstract(mod)) {
			System.out.println("abstract");
			return null;
		}

		Class[] argclass = new Class[args.length];

		for (int i = 0; i < argclass.length; i++) {
			argclass[i] = args[i].getClass();
		}
		Constructor constructor = cls.getDeclaredConstructor(argclass);
		return constructor.newInstance();
	}

	/**
	 * 获得类的静态属性值
	 * 
	 * @param className
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Object getStaticProperty(String className, String fieldName) throws Exception {
		Class cls = Class.forName(className);
		Field field = cls.getDeclaredField(fieldName);
		Object propvalue = field.get(cls);
		return propvalue;
	}

	/**
	 * 执行指定类的指定的方法
	 * 
	 * @param 类的完全限定名
	 * @param 方法名
	 * @param 方法的参数
	 * @return 执行方法后的返回值
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Object invokeMethod(String className, String methodName, Object... args) throws Exception {
		Class cls = Class.forName(className);
		Class[] argclass = new Class[args.length];
		for (int i = 0; i < argclass.length; i++) {
			argclass[i] = args[i].getClass();
		}
		Method method = cls.getDeclaredMethod(methodName, argclass);
		return method.invoke(getNewInstanceByClassName(className), args);
	}

	/**
	 * 运行指定静态类的方法
	 * 
	 * @param 类的完全限定名
	 * @param 方法名
	 * @param 参数
	 * @return 目标方法的返回值
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Object invokeStaticMethod(String className, String methodName, Object... args) throws Exception {
		Class cls = Class.forName(className);
		Class[] argclass = new Class[args.length];

		for (int i = 0; i < argclass.length; i++) {
			argclass[i] = args[i].getClass();
		}
		Method method = cls.getDeclaredMethod(methodName, argclass);
		return method.invoke(null, args);
	}
}
