package j.se.jmx.mbean.hello;

import javax.management.Notification;
import javax.management.NotificationListener;

public class HelloListener implements NotificationListener {
	/*
	 * (non-Javadoc)
	 * @see javax.management.NotificationListener#handleNotification(javax.management.Notification, java.lang.Object)
	 */
	@Override
	public void handleNotification(Notification notification, Object handback) {
		System.out.println(notification.getMessage());
		System.out.println(notification.getType());
		System.out.println(notification.getUserData().getClass().getName());
		System.out.println(handback.getClass().getName());
	}
}
