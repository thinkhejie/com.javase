package j.se.jmx.mbean.hello;

import javax.management.Notification;
import javax.management.NotificationFilter;

public class HelloFilter implements NotificationFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5698625192612913669L;

	@Override
	public boolean isNotificationEnabled(Notification notification) {
		System.out.println(notification.getMessage());
		return false;
	}
}
