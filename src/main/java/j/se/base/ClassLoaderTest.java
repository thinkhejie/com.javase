package j.se.base;

import java.util.Date;

public class ClassLoaderTest {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(ClassLoaderTest.class.getClassLoader().getClass().getName());
		System.out.println(System.class.getClassLoader());
		System.out.println("xxx");
		ClassLoader loader = ClassLoaderTest.class.getClassLoader();
		while (loader != null) {
			System.out.println(loader.getClass().getName());
			loader = loader.getParent();
		}
		System.out.println(loader);

		// System.out.println(new ClassLoaderAttachment().toString());
		System.out.println("xxx2");
		MyClassLoader  mcl = new MyClassLoader("itcastlib");
		Class<Date> clazz = (Class<Date>) mcl.loadClass("cn.itcast.day2.ClassLoaderAttachment");
		mcl.findClass("cn.itcast.day2.ClassLoaderAttachment");
		Date d1 = clazz.newInstance();
		System.out.println(d1);
	}

}
