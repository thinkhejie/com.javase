package read;

import j.sqlite.SqliteHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import ailk.oa.item.StaffInfo;
import ailk.oa.tools.ContactsPageParse;

public class Test {
	static Connection conn;
	static {
		try {
			Class.forName("org.sqlite.JDBC");
			try {
				conn = DriverManager.getConnection("jdbc:sqlite:c:/contact.db3");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void processData(int j) throws Exception {
		try {
			HttpClient client = new HttpClient();
			List<Header> headers = new ArrayList<Header>();
			headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));
			client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "GB18030");
			client.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
			client.getParams().setAuthenticationPreemptive(true); //日啊设置认证的最关键的一步啊不设置就要搞很久啊
			client.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 1000 * 10);
			client.getParams().setConnectionManagerTimeout(1000 * 10);
			//UsernamePasswordCredentials creds = new UsernamePasswordCredentials(userData.id, userData.password);
			//client.getState().setProxyCredentials(AuthScope.ANY, creds);
			client.getHostConfiguration().setProxy("proxy.zj.chinamobile.com", 8080);
			client.getState().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM, AuthScope.ANY_SCHEME), new UsernamePasswordCredentials("ailk\\hejie2", "654321aA"));
			GetMethod get = new GetMethod("http://intra.asiainfo-linkage.com/misonline/address/employee_search.asp");
			//GetMethod get = new GetMethod("http://home.asiainfo-linkage.com");
			// Tell the GET method to automatically handle authentication. The
			// method will use any appropriate credentials to handle basic
			// authentication requests.  Setting this value to false will cause
			// any request for authentication to return with a status of 401.
			// It will then be up to the client to handle the authentication.
			get.setDoAuthentication(true);
			//client.executeMethod(get);
			PostMethod postMethod = new PostMethod("http://intra.asiainfo-linkage.com/misonline/address/employee_search.asp?txtPage=" + String.valueOf(j));
			postMethod.setDoAuthentication(false);
			//String valueString = "maym2";
			//String txtCondition = "WHERE EI.EMPLOYEE_NUMBER LIKE '%"+ valueString+"%' OR  EI.LAST_NAME  LIKE '%" + valueString + "%'  OR EI.OFFICE LIKE '%" + valueString + "%' OR EI.MOBILE LIKE '%" + valueString + "%' OR EI.NT_ACCOUNT LIKE '%" + valueString + "%' OR EI.ORG_NAME   LIKE '%" + valueString + "%'";
			String txtCondition = "WHERE UPPER(EI.LAST_NAME) LIKE '%" + "%'";//+ " upper(EI.EMPLOYEE_NUMBER) LIKE '%"+ + "%'";
			//String txtCondition = "WHERE UPPER(EI.NT_ACCOUNT) LIKE '%" + "ankun" + "%'";//+ " upper(EI.EMPLOYEE_NUMBER) LIKE '%"+ + "%'";
			/*txtCondition += " OR UPPER(EI.EMPLOYEE_NUMBER) LIKE '%"+ valueString+"%'";
			txtCondition += " OR UPPER(EI.LAST_NAME)  LIKE '%" + valueString + "%'";
			txtCondition += " OR UPPER(EI.OFFICE)  LIKE '%" + valueString + "%'";
			txtCondition += " OR UPPER(EI.MOBILE)  LIKE '%" + valueString + "%'";
			txtCondition += " OR UPPER(EI.ORG_NAME)  LIKE '%" + valueString + "%'";*/
			org.apache.commons.httpclient.NameValuePair[] data = { new org.apache.commons.httpclient.NameValuePair("txtCmd", "search"), new org.apache.commons.httpclient.NameValuePair("txtCondition", txtCondition) };
			//将表单的值放入postMethod中 
			postMethod.setRequestBody(data);
			client.executeMethod(postMethod);
			String resultPage = postMethod.getResponseBodyAsString();
			System.out.println(resultPage);
			ArrayList<StaffInfo> infos = new ContactsPageParse().getStaffInfoViaPage(postMethod.getResponseBodyAsStream());
			postMethod.releaseConnection();// release any connection resources used by the method
			for (StaffInfo paramStaffInfo : infos) {
				new SqliteHelper().insert(conn, paramStaffInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(10);
		final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

		/*模拟处理16行日志，下面的代码产生了16个日志对象，当前代码需要运行16秒才能打印完这些日志。修改程序代码，开四个线程让这16个对象在4秒钟打完。*/
		for (int i = 49; i <= 563; i++) {
			final String log = "" + i;
			try {
				queue.put(log);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < 9; i++) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							String log = queue.take();
							parseLog(log);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			//			new Thread(new Runnable() {
			//				@Override
			//				public void run() {
			//					while (true) {
			//						try {
			//							String log = queue.take();
			//							parseLog(log);
			//						} catch (InterruptedException e) {
			//							e.printStackTrace();
			//						}
			//					}
			//				}
			//			}).start();
		}
		//
		System.out.println("begin: " + (System.currentTimeMillis() / 1000));
	}

	public static void parseLog(String log) {
		System.out.println(log + " : " + (System.currentTimeMillis() / 1000));
		try {
			processData(Integer.valueOf(log));
			Thread.sleep(1000L * 7);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
