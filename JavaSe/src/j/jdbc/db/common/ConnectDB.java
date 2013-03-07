package j.jdbc.db.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成方法存根

	}

	private static final String MYSQL = "jdbc:mysql://";

	private static final String ORACLE = "jdbc:oracle:thin:@";

	private ConnectDB() {
	}

	public static Connection getInstance(String DBType, String url) throws SQLException {
		if ("mysql".equalsIgnoreCase(DBType))
			return getMySqlConn(url);
		if ("oracle".equalsIgnoreCase(DBType))
			return getOracleConn(url);
		return null;
	}

	public static void closeConn(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	private static Connection getMySqlConn(String url) throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		conn = DriverManager.getConnection(MYSQL + url, "root", "root");
		return conn;
	}

	private static Connection getOracleConn(String url) throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		conn = DriverManager.getConnection(ORACLE + url, "scott", "tiger");
		return conn;
	}

}
