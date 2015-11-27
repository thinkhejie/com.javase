package j.se.aop;

/**
 * @author hejie
 */
public class HelloImp implements IHello {

	@Override
	public void toHello(String name) {
		System.out.println("hello:" + name);
	}

}
