package j.se.aop;

public class TestHelloWorld {

	public static void main(String[] args) {
		HelloWorld mb = new HelloWorldImpl();
		HelloWorld bi = (HelloWorld) ProxyFactory.getProxy(mb);
		bi.say();

		ProxyFactory.getBean(HelloWorld.class);
	}
}
