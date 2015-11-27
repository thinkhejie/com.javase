package j.se.dynamic.proxy;

public class ProxyDemo {
	public static void main(String[] args) throws SecurityException, NoSuchMethodException {
		LogHandler logHandler = new LogHandler();
		IHello hello = (IHello) logHandler.bind(new HelloImp());
		hello.toHello("hej");
	}
}
