package j.jdbc.xa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import oracle.jdbc.xa.client.OracleXADataSource;

import org.junit.Test;

/*
 * 
Java Transaction API 允许您操作应用程序中的分布式事务（Distributed Transaction）。

JTA 中有一组方法，它将传统的 JDBC 调用封装到了两阶段提交（Two-Phase-Commit）协议中。

在异构环境中，您通常会发现一个事务管理器（Transaction Manager），负责处理分布式事务。
（实际上，事务管理器可以完成大量的工作负载平衡。）
因此，不仅存在到数据库的直接连接，还有到事务管理器（Transaction Manager）的连接。
这就是 JTA 发挥作用的地方：JTA 是 Java 应用程序和事务管理器（Transaction Manager）之间的接口。

图 2 演示了一个包含分布式事务的典型环境。

由于存在事务管理器（Transaction Manager），它通常包含在应用程序服务器（Application Server）中，就不再有两层（Two-Tier）架构。

传统的客户/服务器（Client/Server）架构已经由三层（Tree-Tier）架构所取代，三层架构包含应用程序/客户机、事务管理器（Transaction Manager）/应用程序服务器（Application Server）和数据库服务器，而数据库服务器一般称作 XA Resource。

 */
public class XaDemo {
	private static void sqlerr(SQLException exception) {
		System.err.println("FAILED.");
		while (exception != null) {
			System.err.println("==> SQL Exception caught");
			System.err.println("--> SQLCODE : " + exception.getErrorCode());
			System.err.println("--> SQLSTATE: " + exception.getSQLState());
			System.err.println("--> Message : " + exception.getMessage());
			exception = exception.getNextException();
		}
	}

	/*
	  在使用JTA之前，你必须首先实现一个Xid类用来标识事务（在普通情况下这将由事务管理程序来处理）。
	 Xid包含三个元素：formatID、gtrid（全局事务标识符）和bqual（分支修饰词标识符）。
	 */
	static void commitBranch(XAResource xares, Xid xid) {
		System.out.print("Commit XA branch (" + Byte.toString((xid.getGlobalTransactionId())[0]) + ", " + Byte.toString((xid.getBranchQualifier())[0]) + "): ");
		try {
			// second parameter is 'false' since we have a two phase commit
			xares.commit(xid, false);
		} catch (XAException e) {
			xaerr(e);
		}
		System.out.println("Okay.");
	}

	@Test
	public void ttt() throws FileNotFoundException, IOException {
		String propertyfile = "jtadb2ifmx.properties";
		InputStream str = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyfile);
		System.out.println(str == null);
		Properties props = new Properties();
		props.load(str);
		String sql = props.getProperty("sql.statement");
		System.out.println(sql);
		//
		//URL str1 = XaDemo.class.getResource("\\\\config\\\\" + propertyfile);//得到的是当前的classpath的绝对URI路径
		//System.out.println(str1.getPath());
		//InputStream i = str1.openStream();
		Properties p1 = new Properties();
		p1.load(ClassLoader.getSystemResourceAsStream(propertyfile));
		String p12 = p1.getProperty("sql.statement");
		System.out.println(p12);
		InputStream in = new FileInputStream(propertyfile);
		System.out.println(in == null);
	}

	static void execBranch(Connection con, XAResource xares, Xid xid) {
		try {
			xares.start(xid, javax.transaction.xa.XAResource.TMNOFLAGS);
			Statement stmt = con.createStatement();
			stmt.executeUpdate("insert into test_table (USER_ID) values (10000)");
			xares.end(xid, javax.transaction.xa.XAResource.TMSUCCESS);
		} catch (XAException e) {
			System.err.println("XA exception caught:");
			System.err.println("Cause  : " + e.getCause());
			System.err.println("Message: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			sqlerr(e);
		}
	}

	/**
	 * @param requiredType  
	 */
	static <T> T getValueFromProFile(String key, Class<T> requiredType) {
		Properties props = new Properties();
		try {
			String propertyfile = "jtadb2ifmx.properties";
			props.load(new FileInputStream(propertyfile));
			Object obj = props.get(key);
			return (T) obj;
		} catch (Exception e) {
			return null;
		}
	}

	static XADataSource initOracleXADataSource(String host, int port, String serviceName, String userName, String pwd) throws SQLException {
		System.out.print("Create an IDS XA data source: ");
		OracleXADataSource ds = new OracleXADataSource();
		ds.setDescription("IDS XA data source");
		ds.setURL("jdbc:oracle:thin:@(description=(address=(host=" + host + ")(protocol=tcp)(port=" + String.valueOf(port) + "))(connect_data=(SERVICE_NAME=" + serviceName + ")))");
		ds.setUser(userName);
		ds.setPassword(pwd);
		System.out.println("Okay.");
		return ds;
	}

	static int prepareCommit(XAResource xares, Xid xid) {
		int rc = 0;
		System.out.print("Prepare XA branch (" + Byte.toString((xid.getGlobalTransactionId())[0]) + ", " + Byte.toString((xid.getBranchQualifier())[0]) + "): ");
		try {
			rc = xares.prepare(xid);
		} catch (XAException e) {
			xaerr(e);
		}
		System.out.println("Okay.");
		return rc;
	}

	static void rollbackBranch(XAResource xares, Xid xid) {
		System.out.print("Rollback XA branch (" + Byte.toString((xid.getGlobalTransactionId())[0]) + ", " + Byte.toString((xid.getBranchQualifier())[0]) + "): ");
		try {
			xares.rollback(xid);
		} catch (XAException e) {
			xaerr(e);
		}
		System.out.println("Okay.");
	}

	static void xaerr(XAException exception) {
		System.err.println("FAILED.");
		System.err.println("==> XA Exception caught");
		System.err.println("--> Cause  : " + exception.getCause());
		System.err.println("--> Message: " + exception.getMessage());
		exception.printStackTrace();
	}

	public static void main(String[] arg) throws SQLException {
		XADataSource xaDS = initOracleXADataSource("10.70.193.15", 2001, "zjfsdev", "so1", "so1");
		XADataSource xaDS2 = initOracleXADataSource("10.70.193.17", 1521, "zjyscrm", "so1", "so1");
		XAConnection xaCon2 = xaDS2.getXAConnection();
		XAResource xaRes2 = xaCon2.getXAResource();
		Connection con2 = xaCon2.getConnection();
		XAConnection xaCon;
		XAResource xaRes;
		Xid xid = new MyXid(100, new byte[] { 0x01 }, new byte[] { 0x02 });
		Xid xid2 = new MyXid(100, new byte[] { 0x01 }, new byte[] { 0x02 });
		Connection con;
		xaCon = xaDS.getXAConnection();
		xaRes = xaCon.getXAResource();
		con = xaCon.getConnection();
		//		try {
		//			xaRes.start(xid, XAResource.TMNOFLAGS);
		//			stmt.executeUpdate("insert into test_table (USER_ID) values (100)");
		//			stmt2.executeUpdate("insert into test_table (USER_ID) values (100)");
		//			xaRes.end(xid, XAResource.TMSUCCESS);
		//			ret = xaRes.prepare(xid);
		//			if (ret == XAResource.XA_OK) {
		//				xaRes.commit(xid, false);
		//			}
		//		} catch (XAException e) {
		//			e.printStackTrace();
		//			try {
		//				xaRes.rollback(xid);
		//			} catch (XAException e1) {
		//				e1.printStackTrace();
		//			}
		//		} finally {
		//			stmt.close();
		//			con.close();
		//			xaCon.close();
		//		}
		// these methods contain the actual SQL statements.
		// after execution the transaction is NOT committed.
		System.out.println("\nIBM JTA DEMO for DB2 UDB and Informix Dynamic Server");
		System.out.println("============================================================" + "==================");
		System.out.println("This is a demo using JTA with DB2 UDB and Informix. Make " + "you have both the DB2");
		System.out.println("and Informix JDBC driver included in your CLASSPATH.");
		System.out.println("Environment dependend parameters (such as connection " + "settings) are stored in");
		System.out.println("the properties file called 'jtadb2ifmx.properties'.");
		System.out.println();
		execBranch(con, xaRes, xid);
		execBranch(con2, xaRes2, xid2);
		if (prepareCommit(xaRes, xid) == XAResource.XA_OK && prepareCommit(xaRes2, xid2) == XAResource.XA_OK) {
			// both branches are ready to commit
			commitBranch(xaRes, xid);
			commitBranch(xaRes2, xid2);
		} else {
			// a resource reported an error
			rollbackBranch(xaRes, xid);
			rollbackBranch(xaRes2, xid2);
		}
	}
}
