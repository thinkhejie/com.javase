package j.se.jmx.mbean.jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.util.HashMap;
import java.util.Map;

public class JVM5Monitor implements JVM5MonitorMBean {

	@Override
	public Map getThreadInfo() throws Exception {
		Map map = new HashMap();
		long[] threadIds = ManagementFactory.getThreadMXBean().getAllThreadIds();
		ThreadInfo[] objThreadInfo = ManagementFactory.getThreadMXBean().getThreadInfo(threadIds, 2147483647);
		for (int i = 0; i < objThreadInfo.length; ++i) {
			map.put(objThreadInfo[i].getThreadName(), objThreadInfo[i].getThreadState().name());
		}
		return map;
	}

	@Override
	public String getAllThreadInfo() throws Exception {
		return null;
	}

	@Override
	public String getMemoryInfo() throws Exception {
		return null;
	}

}
