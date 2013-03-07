package j.se.rmi;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;

public class HelloImpl implements RmiHello {
	Connection cc = t();
	//extends UnicastRemoteObject
	/**
	 * 
	 */
	private static final long serialVersionUID = 4537486006376515918L;

	/**
	 * 因为UnicastRemoteObject的构造方法抛出了RemoteException异常，因此这里默认的构造方法必须写，
	 * 必须声明抛出RemoteException异常
	 * 
	 * @throws RemoteException
	 */
	public HelloImpl() throws RemoteException {
	}

	/**
	 * 返回"Hello World！"
	 * 
	 * @return 返回"Hello World！"字样
	 * @throws java.rmi.RemoteException
	 */
	@Override
	public String helloWorld() throws RemoteException {
		return "Hello World!";
	}

	/**
	 * 一个简单的业务方法，根据传入的人名返回相应的问候语
	 * 
	 * @param someBodyName
	 *            人名
	 * @return 返回相应的问候语
	 * @throws java.rmi.RemoteException
	 */
	@Override
	public String sayHelloToSomeBody(String someBodyName) throws RemoteException {
		return "你好，" + someBodyName + "!";
	}

	public Connection sayHelloToSomeBody1(String someBodyName) throws RemoteException, Exception {
		Connection c = t();
		return c;
	}

	public Connection t() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/base";
			String user = "root";
			String password = "admin";
			Connection conn = DriverManager.getConnection(url, user, password);
			System.err.println(conn.getClass().getName());
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
