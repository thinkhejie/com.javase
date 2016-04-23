package j.reflect;

import j.reflect.Entity;
import j.reflect.RefelctHelper;

import org.junit.BeforeClass;
import org.junit.Test;


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
