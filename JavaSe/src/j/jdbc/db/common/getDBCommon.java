package j.jdbc.db.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author hej
 *
 */

public class getDBCommon {

	private String driver;
	private String url;
	private String user;
	private String password;
	private Connection conn;
	private Statement stm;
	private ResultSet rs;

	public getDBCommon() {
		this("DBConf.properties");
	}

	public getDBCommon(String conf) {
		loadProperties(conf);
		setConn();
	}

	public Connection getConn() {
		return conn;
	}

	//handle the properties file to get the informations for connection
	private void loadProperties(String conf) {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(conf));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		driver = props.getProperty("driver");
		url = props.getProperty("url");
		user = props.getProperty("user");
		password = props.getProperty("password");
	}

	//implement the Connection

	private void setConn() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException classnotfoundexception) {
			classnotfoundexception.printStackTrace();
			System.err.println("db: " + classnotfoundexception.getMessage());
		} catch (SQLException sqlexception) {
			System.err.println("db.getconn(): " + sqlexception.getMessage());
		}
	}

	/**
	 * @author hej
	 * @param  sql
	 * @return Statement
	 */
	public int doInsert(String sql) {
		try {
			Statement statement = conn.createStatement();
			int i = statement.executeUpdate(sql);
			return i;
		} catch (SQLException sqlexception) {
			System.err.println("db.executeInset:" + sqlexception.getMessage());
			return 0;
		}
	}

	public int doDelete(String sql) {
		try {
			stm = conn.createStatement();
			int i = stm.executeUpdate(sql);
			return i;
		} catch (SQLException sqlexception) {
			System.err.println("db.executeDelete:" + sqlexception.getMessage());
			return 0;
		}
	}

	/**
	 * @author hej
	 * @param  sql
	 * @return Statement
	 */
	public int doUpdate(String sql) {
		try {
			stm = conn.createStatement();
			int i = stm.executeUpdate(sql);
			return i;
		} catch (SQLException sqlexception) {
			System.err.println("db.executeUpdate:" + sqlexception.getMessage());
			return 0;
		}
	}

	/**
	 * @author hej
	 * @param  sql
	 * @return Statement
	 */
	public ResultSet doSelect(String sql) {
		try {
			stm = conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery(sql);
		} catch (SQLException sqlexception) {
			System.err.println("db.executeQuery: " + sqlexception.getMessage());
		}
		return rs;
	}

	/**
	 * @author hej
	 * @param  sql
	 * @return Statement
	 */
	public static void main(String[] args) {
		try {
			getDBCommon db = new getDBCommon();
			Connection conn = db.getConn();
			if (conn != null && !conn.isClosed()) {
				System.out.println("�B�Y�ɹ�");
				ResultSet rs = db.doSelect("select * from content");
				while (rs.next()) {
					System.out.println(rs.getString(1) + ":" + rs.getString(2) + ":" + rs.getString(3));
				}
				rs.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
