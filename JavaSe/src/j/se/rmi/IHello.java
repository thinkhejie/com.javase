package j.se.rmi;

import java.rmi.RemoteException;
import java.sql.Connection;

/**
 * 锟斤拷锟斤拷一锟斤拷远锟教接口ｏ拷锟斤拷锟斤拷坛锟絉emote锟接口ｏ拷锟斤拷锟斤拷锟斤拷要远锟教碉拷锟矫的凤拷锟斤拷锟斤拷锟斤拷锟阶筹拷RemoteException锟届常
 */
//extends Remote
public interface IHello {

	/**
	 * 锟津单的凤拷锟截★拷Hello World锟斤拷"锟斤拷锟斤拷
	 * 
	 * @return 锟斤拷锟截★拷Hello World锟斤拷"锟斤拷锟斤拷
	 * @throws java.rmi.RemoteException
	 */
	String helloWorld() throws RemoteException;

	/**
	 * 一锟斤拷锟津单碉拷业锟今方凤拷锟斤拷锟斤拷荽锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟接︼拷锟斤拷屎锟斤拷锟?
	 * 
	 * @param someBodyName
	 *            锟斤拷锟斤拷
	 * @return 锟斤拷锟斤拷锟斤拷应锟斤拷锟绞猴拷锟斤拷
	 * @throws java.rmi.RemoteException
	 */
	String sayHelloToSomeBody(String someBodyName) throws RemoteException;

	Connection sayHelloToSomeBody1(String someBodyName) throws RemoteException, Exception;
}
