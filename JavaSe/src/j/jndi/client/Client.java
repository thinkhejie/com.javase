package j.jndi.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

public class Client {
	/*
	   java.naming.factory.initial  创建上下文的工厂，如果没有找到支持对应 name 上下文环境，就用这个工厂创建 创建默认的 context
	   java.naming.provider.url ，用来配置 context 的初始 url 
	   java.naming.factory.object  创建特定的对象的工厂 
	   java.naming.factory.state ，用来创建查询 jndi state 状态的工厂 
	   java.naming.factory.url.pkgs  包名列表，在指定的包名下查找创建特定 url 的上下文的工厂，如果没有，则使用 
	   java.naming.factory.initial 创建的上下文 
	*/
	@org.junit.Test
	public void test() throws Exception {
		Properties props = new Properties();
		props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
		//props.setProperty("java.naming.factory.initial", "org.apache.naming.java.javaURLContextFactory");
		props.setProperty(Context.PROVIDER_URL, "localhost");
		Context context = new InitialContext(props);
		Context ctx = (Context) context.lookup("java:comp/env");
		System.out.println("ctx: " + ctx);
		//DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/TestDB");
		// "java:/comp/env/"是固定写法，后面接的是//context.xml中的Resource中name属性的值 
		//Connection conn = ds.getConnection();
		//System.out.println(conn);
		//Statement stmt = conn.createStatement();
		//ResultSet set = stmt.executeQuery("SELECT cfg_remote_code FROM base.cfg_remote;");
		//while (set.next()) {
		//System.out.println(set.getString("name"));
		//}
	}
}
