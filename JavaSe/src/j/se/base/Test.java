package j.se.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public class Test extends Thread {

	// 静态变量        
	public static String staticField = "静态变量";
	// 变量        
	public String field = "变量";

	// 静态初始化块        
	static {
		System.out.println(staticField);
		System.out.println("静态初始化块");
	}

	// 初始化块        
	{
		System.out.println(field);
		System.out.println("初始化块");
	}

	public static void main(String[] args) {
		//Thread t =  new Test(); 
		//t.start();
		//t.run();	  	
		List<? extends Object> al = new ArrayList<String>();
		List<?> alll = new ArrayList<String>();
		List<? super String> ext = new ArrayList<Object>();
		ArrayList<String> al1 = new ArrayList<String>();
		Collection<String> cs = new ArrayList<String>();
		TreeSet ts = new TreeSet();
		ts.add("a");
		ts.add("A");
		System.out.println(ts);

	}

	@Override
	public void run() {
		System.out.println("11111");
	}
}
