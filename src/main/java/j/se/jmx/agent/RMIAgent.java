package j.se.jmx.agent;

import j.se.jmx.mbean.hello.Hello;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXServiceURL;

public class RMIAgent {

	public static void main(String[] args) throws Exception {
		/**
		* 使用Jconsole
		*/
		Hello hw = new Hello();
		MBeanServer mbsJconsole = ManagementFactory.getPlatformMBeanServer();
		MBeanServer mbs = MBeanServerFactory.createMBeanServer();
		JMXConnectorServer connector = startRMIConnector(mbs);
		try {
			ObjectName connectorName1 = new ObjectName("JMXBookAgent:name=RMIConnector");
			ObjectName connectorName2 = new ObjectName("HelloAgent:name=RMIConnector");
			ObjectName helloWorldName = new ObjectName("HelloAgent:name=helloWorld1");
			mbs.registerMBean(connector, connectorName1);
			mbs.registerMBean(connector, connectorName2);
			mbs.registerMBean(hw, helloWorldName);
			connector.start();
			System.out.println("start......");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Listing 3.3 startRMIConnector() method that adds the RMI  connector  server to the agent
	protected static JMXConnectorServer startRMIConnector(MBeanServer server) throws IOException {
		LocateRegistry.createRegistry(8077);
		//--
		//--
		//MBeanServer server = MBeanServerFactory.createMBeanServer();
		//		ObjectName namingName = ObjectName.getInstance("naming:type=rmiregistry");
		//		server.createMBean("mx4j.tools.naming.NamingService", namingName, null);
		//		server.setAttribute(namingName, new Attribute("Port", new Integer(2099)));
		//		server.invoke(namingName, "start", null, null);
		//--
		//javax.management.remote.rmi.RMIConnectorServer
		//String urlStr = "service:jmx:rmi://localhost/jndi/rmi://" + InetAddress.getLocalHost().getHostAddress() + ":" + 2099 + "/jmxconnector";
		//-------------//service:jmx:rmi:///jndi/rmi://localhost:8077/jmxconnector1
		String urlStr = "service:jmx:rmi:///jndi/rmi://" + "127.0.0.1" + ":" + 8077 + "/jmxconnector1";
		JMXServiceURL url = new JMXServiceURL(urlStr);
		HashMap environment = new HashMap();
		javax.management.remote.rmi.RMIConnectorServer connector = new javax.management.remote.rmi.RMIConnectorServer(url, environment, server);
		//--
		//JMXConnectorServer jMXConnectorServer = JMXConnectorServerFactory.newJMXConnectorServer(url, environment, server);
		//jMXConnectorServer.start();
		connector.start();
		return connector;
	}
}
