package j.sqlite;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import ailk.oa.item.StaffInfo;

public class SqliteHelper {
	//private Connection conn = null;

	public SqliteHelper() {
		//		try {
		//			//Class.forName("org.sqlite.JDBC");
		//			//this.conn = conn;
		//			//this.conn = DriverManager.getConnection("jdbc:sqlite:c:/contact.db3");
		//			//conn.setTransactionIsolation(Connection.TRANSACTION_NONE);
		//			//	conn.setAutoCommit(true);
		//		} catch (ClassNotFoundException e) {
		//			e.printStackTrace();
		//		} catch (SQLException e) {
		//			e.printStackTrace();
		//		}
	}

	public void main(String[] args) throws Exception {
		//建立事务机制,禁止自动提交，设置回滚点                                                                           
		//	Statement stat = conn.createStatement();
		//stat.executeUpdate("create table people (name, occupation);");
		//stat.executeUpdate("insert into people values ('Gandhi', 'politics');");
		//stat.executeUpdate("insert into people values ('Turing', 'computers');");
		//stat.executeUpdate("insert into people values ('Wittgenstein', 'smartypants');");
		//conn.commit();
		//ResultSet rs = stat.executeQuery("select * from people;");
		//		while (rs.next()) {
		//			System.out.println("name = " + rs.getString("name"));
		//			System.out.println("occupation = " + rs.getString("occupation"));
		//		}
		//
		//		rs.close();
		//		conn.close();
	}

	public long insert(Connection conn1, StaffInfo paramStaffInfo) throws SQLException {
		Statement stat = conn1.createStatement();
		try {
			String str3 = paramStaffInfo.getLastName();
			String str4 = paramStaffInfo.getEmployeeNumber();
			String str5 = paramStaffInfo.getOrgName();
			String str6 = paramStaffInfo.getLocal();
			String str7 = paramStaffInfo.getOffice();
			String str8 = paramStaffInfo.getMobile();
			String str9 = paramStaffInfo.getNtAccount();
			String str10 = paramStaffInfo.getEmail();
			String str11 = paramStaffInfo.getPosition();
			String str12 = paramStaffInfo.getBranch();
			String sql = "insert into td_ailk_staff  (last_name ,employee_number ,org_name ,local ,office ,mobile ,nt_account ,email ,position ,branch ,id)  VALUES (";
			StringBuilder sb = new StringBuilder(sql);
			sb.append("'" + str3 + "' ,");
			sb.append("'" + str4 + "' ,");
			sb.append("'" + str5 + "' ,");
			sb.append("'" + str6 + "' ,");
			sb.append("'" + str7 + "' ,");
			sb.append("'" + str8 + "' ,");
			sb.append("'" + str9 + "' ,");
			sb.append("'" + str10 + "' ,");
			sb.append("'" + str11 + "' ,");
			sb.append("'" + str12 + "' ,");
			sb.append("null");
			sb.append(" );");
			System.out.println(sb.toString());
			int i = stat.executeUpdate(sb.toString());
			//conn.commit();
			return i;
		} catch (Exception localException1) {
			//conn.rollback();
			localException1.printStackTrace();
			return -1;
		} finally {

		}
	}
}
