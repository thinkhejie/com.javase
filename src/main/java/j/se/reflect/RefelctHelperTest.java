package j.se.reflect;

import org.junit.BeforeClass;
import org.junit.Test;

import j.se.reflect.Entity;
import j.se.reflect.RefelctHelper;


public class RefelctHelperTest {

	Entity ins = null;
	static RefelctHelper helper = null;

	@BeforeClass
	public static void kkk() {
		helper = new RefelctHelper();
	}

	@Test
	public void testGetNewInstanceByClassName() throws Exception {
		ins = (Entity) helper.getNewInstanceByClassName("com.reflect.U1");
		System.out.println(ins);
	}

	@Test
	public void testInvokeMethod() throws Exception {
		String s = (String) helper.invokeMethod("com.reflect.Entity", "helloworld", "aaaa");
		System.out.println(s);
	}
}
