package j.se.rmi.client;

import j.se.rmi.IHello;

import java.rmi.Naming;

import org.junit.BeforeClass;
import org.junit.Test;

public class RmiClient {
	private static IHello rhello = null;

	@BeforeClass
	public static void beforeClass() throws Exception {
		rhello = (IHello) Naming.lookup("rmi://localhost:8888/hejie");
	}

	@Test
	public void main0() throws Exception {
		//锟斤拷RMI锟斤拷锟斤拷注锟斤拷锟斤拷胁锟斤拷锟斤拷锟斤拷为RHello锟侥讹拷锟襟，诧拷锟斤拷锟斤拷锟斤拷锟较的凤拷锟斤拷
		//IHello rhello = (IHello) Naming.lookup("rmi://localhost:8888/hejie");
		System.out.println(rhello.helloWorld());
		System.out.println(rhello.sayHelloToSomeBody("112333"));
		System.out.println(rhello.sayHelloToSomeBody1("112333"));
	}

	@Test
	public void testSayHelloToSomeBody() throws Exception {
		System.out.println(rhello.sayHelloToSomeBody("112333"));
		//System.out.println(rhello.sayHelloToSomeBody1("112333"));
	}

	@Test
	public void main() throws Exception {
		System.out.println(rhello.helloWorld());
		System.out.println(rhello.sayHelloToSomeBody("hello"));
	}
}
