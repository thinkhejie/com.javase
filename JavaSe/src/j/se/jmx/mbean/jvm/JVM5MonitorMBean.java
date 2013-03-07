package j.se.jmx.mbean.jvm;

import java.util.Map;

public interface JVM5MonitorMBean {
	public Map getThreadInfo() throws Exception;

	public String getAllThreadInfo() throws Exception;

	public String getMemoryInfo() throws Exception;
}