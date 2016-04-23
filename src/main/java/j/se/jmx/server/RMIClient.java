package j.se.jmx.server;

import j.se.jmx.mbean.hello.HelloMBean;

import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;
import org.quartz.SchedulerException;

public class RMIClient {
	private static final String DEFAULT_PROVIDER_URL = "hejie://localhost:8082";
	private static final String RMI_ADAPTOR_JNDI_NAME = "hejie";

	protected static Properties getContextProperties() {
		Properties props = new Properties();
		//jndi上下文的工厂类(java.naming.factory.initial)
		//props.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
		//
		//props.put(javax.naming.Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		//
		props.put(javax.naming.Context.PROVIDER_URL, "hejie://localhost:8082");

		return props;
	}

	public static void main(String[] args) throws Exception {
		InitialContext ctx = null;
		try {
			ctx = new InitialContext(getContextProperties());
			MBeanServerConnection server = (MBeanServerConnection) ctx.lookup(RMI_ADAPTOR_JNDI_NAME);
		} catch (Exception e) {
			throw new SchedulerException("Failed to lookup JBoss JMX RMI Adaptor.", e);
		} finally {
			if (ctx != null) {
				try {
					ctx.close();
				} catch (NamingException ignore) {
				}
			}
		}
	}

	@Test
	public void testRMIClient() throws Exception {
		final AtomicInteger count = new AtomicInteger();
		HashMap<String, Object> prop = new HashMap<String, Object>();
		//prop.put(JMXConnector.CREDENTIALS, "hello");
		JMXConnector conn = JMXConnectorFactory.connect(new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:8077/jmxconnector1"), prop);
		conn.connect();
		HelloMBean hello = JMX.newMBeanProxy(conn.getMBeanServerConnection(), new ObjectName("HelloAgent:name=helloWorld1"), HelloMBean.class);
		hello.setName("bearice");
		hello.printHello();
		System.out.println(count.incrementAndGet());
	}
}
