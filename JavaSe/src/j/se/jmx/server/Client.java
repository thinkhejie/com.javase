package j.se.jmx.server;

import j.se.jmx.mbean.hello.HelloMBean;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.management.JMX;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class Client {

	static HelloMBean hello;

	public static void main(String[] args) throws Exception {
		final AtomicInteger count = new AtomicInteger();
		HashMap<String, Object> prop = new HashMap<String, Object>();
		prop.put(JMXConnector.CREDENTIALS, "hello");
		final JMXConnector conn = JMXConnectorFactory.connect(new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:8877/hello"), prop);
		conn.connect();
		Runnable r = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						final HelloMBean hello = JMX.newMBeanProxy(conn.getMBeanServerConnection(), new ObjectName("hello:name=hello"), HelloMBean.class);
						hello.setName("bearice");
						hello.printHello();
						System.out.println(count.incrementAndGet());
						Client.hello = hello;
						//System.out.println(hello.equals(hello.getThis()));
					} catch (Exception ex) {
						//Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		};
		Thread[] ts = new Thread[5];
		for (int i = 0; i < ts.length; i++) {
			ts[i] = new Thread(r);
			ts[i].start();
		}
		for (Thread t : ts) {
			t.join();
		}
		//		System.out.println(hello.equals(hello.getThis()));
		//		System.out.println(hello.getId());
		//		System.out.println(hello);
	}
}
