package j.se.jmx.mbean.hello;

import javax.management.ListenerNotFoundException;
import javax.management.MBeanNotificationInfo;
import javax.management.NotificationEmitter;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;

public class Hello implements HelloMBean, NotificationEmitter {
	private String name;

	@Override
	public String getName() {
		return name;

	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void printHello() {
		System.out.println("Hello World, " + name);
	}

	@Override
	public void printHello(String whoName) {
		System.out.println("Hello , " + whoName);

	}

	@Override
	public void addNotificationListener(NotificationListener listener, NotificationFilter filter, Object handback) throws IllegalArgumentException {
		System.out.println(listener.getClass().getName());
		System.out.println("handback: " + handback);
		System.out.println("addNotificationListener");
		listener.handleNotification(null, handback);
	}

	@Override
	public void removeNotificationListener(NotificationListener listener) throws ListenerNotFoundException {
		System.out.println("removeNotificationListener");
	}

	@Override
	public MBeanNotificationInfo[] getNotificationInfo() {
		System.out.println("getNotificationInfo");
		return null;
	}

	@Override
	public void removeNotificationListener(NotificationListener listener, NotificationFilter filter, Object handback) throws ListenerNotFoundException {
		System.out.println("removeNotificationListener");
	}
}
