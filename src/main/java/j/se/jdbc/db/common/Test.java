package j.se.jdbc.db.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class Test {

	Integer I;
	int x;

	public enum Dir {
		n, w
	};

	public Test(int y) {
		x = I + y;
		System.out.println(x);
	}

	public static void main(String[] args) {
		// TODO 自动生成方法存根

		HashSet<Object> hs = new HashSet<Object>();
		PriorityQueue<String> pq = new PriorityQueue<String>();
		pq.add("carrot");
		pq.add("apple");
		pq.add("banana");
		System.out.println(pq);
		//Collections.sort();
		System.out.println(pq.poll() + "   " + pq.peek());
		System.out.println("                  ");
		Test.Dir d = Test.Dir.n;

		//new Test(new Integer(4));
		Integer I;
		String str1 = new String("420");
		String str2 = new String("420");

		hs.add(str1);
		hs.add(str2);
		System.out.println("hs::::" + hs.size());

		HashMap hm = new HashMap();

		Set s = hm.keySet();

		s = new TreeSet(s);

		str1 += 42;
		String test = "This is a test";
		String[] tokens = test.split("\\s ");
		ArrayList al = new ArrayList();
		Iterator it = al.iterator();

		Float pi = new Float(3.14f);
		if (pi > 3) {
			System.out.println(pi);
		}
		Animal ai = new Dog();
		String s1 = ai.noise();
		System.out.println(s);
		float f = (float) 9.0;

		System.out.println("daaas");
		for (int k = 0; k < 4; ++k) {

			System.out.println(k);
		}
		new Boolean("false").toString();
		Object o = new Object();

		int x = 5;
		switch (x) {
		case 1:
			int i;
		case 2:
			int k;
		}

		int i = 0;

		//for(; true;i++)
		//{
		//System.out.println("i"+i);
		//if(i/i++ >0) break;
		//}

	}
}
