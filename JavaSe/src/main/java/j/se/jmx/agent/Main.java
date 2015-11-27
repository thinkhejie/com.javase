package j.se.jmx.agent;

import j.se.jmx.mbean.hello.Hello;

import java.rmi.registry.LocateRegistry;
import java.util.HashMap;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

public class Main {

	/**                                                                                                               
	 * @param args the command line arguments                                                                         
	 */
	public static void main(String[] args) throws Exception {
		LocateRegistry.createRegistry(8877);
		MBeanServer server = MBeanServerFactory.createMBeanServer();
		ObjectName helloName = new ObjectName("hello:name=hello");
		Hello hello = new Hello();
		HashMap<String, Object> prop = new HashMap<String, Object>();
		//		prop.put(JMXConnectorServer.AUTHENTICATOR, new JMXAuthenticator() {
		//			@Override
		//			public Subject authenticate(Object credentials) {
		//				System.out.println(String.valueOf(credentials));
		//				if (credentials instanceof String) {
		//					if (credentials.equals("hello")) {
		//						return new Subject();
		//					}
		//				} else if (credentials instanceof String[]) {
		//					return new Subject();
		//				}
		//				return new Subject();
		//				//throw new SecurityException("not authicated");
		//			}
		//		});
		JMXConnectorServer cserver = JMXConnectorServerFactory.newJMXConnectorServer(new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:8877/hello"), prop, server);
		cserver.start();
		server.registerMBean(hello, helloName);
		for (ObjectInstance object : server.queryMBeans(null, null)) {
			System.out.println(object.getObjectName());
		}
		System.out.println(hello);
		System.out.println("start.....");
	}
}
