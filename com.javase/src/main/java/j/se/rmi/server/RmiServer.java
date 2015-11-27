package j.se.rmi.server;

import j.se.rmi.HelloImpl;
import j.se.rmi.RmiHello;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class RmiServer {
	public static void main(String args[]) throws Exception {
		main0();
	}

	public static void main0() throws Exception {
		RmiHello rhello = new HelloImpl();
		RmiHello rmiScheduler = (RmiHello) UnicastRemoteObject.exportObject(rhello, 8888);//杩欓噷HelloImpl涓嶈兘缁ф壙UnicastRemoteObject
		Registry registry = LocateRegistry.createRegistry(8888);
		registry.rebind("hejie", rmiScheduler);
		//registry.unbind(name);
		System.out.println(Arrays.toString(registry.list()));
	}

	public static void main1() {
		try {
			RmiHello rhello = new HelloImpl();
			LocateRegistry.createRegistry(8888);
			//娉ㄥ唽閫氳绔彛
			Naming.bind("//localhost:8888/RHello", rhello);
			System.out.println(">>>>>INFO:远锟斤拷IHello锟斤拷锟斤拷蠖ǔ晒锟斤拷锟?");
		} catch (RemoteException e) {
			System.out.println("锟斤拷锟斤拷远锟教讹拷锟斤拷锟斤拷锟届常锟斤拷");
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			System.out.println("锟斤拷锟斤拷锟截革拷锟襟定讹拷锟斤拷锟届常锟斤拷");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("锟斤拷锟斤拷URL锟斤拷锟斤拷锟届常锟斤拷");
			e.printStackTrace();
		}
	}
}
