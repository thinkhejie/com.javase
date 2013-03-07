package arithmetic;

import java.util.HashMap;
import java.util.Hashtable;

public class HashMapVSHashTable {

	/**
	 * 
	 * 　2、同步性:Hashtable是线程安全的，也就是说是同步的，而HashMap是线程序不安全的，不是同步的。
	*   3、值：只有HashMap可以让你将空值作为一个表的条目的key或value。
	 *   4、HashMap打印出是排序的，HashTable不是排序的。
	 */
	public static void main(String[] args) {
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
		hm.put(7, "abc");
		hm.put(2, "abcd");
		hm.put(3, "abce");
		hm.put(4, "abck");
		ht.put(5, "abclllllllllllllllllll");
		ht.put(2, "abcd");
		ht.put(3, "abce");
		ht.put(4, "abck");
		System.out.println(hm);
		System.out.println(ht);

	}

}
