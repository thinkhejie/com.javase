package arithmetic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class ManyList {

	/**
	 * 1、Vector 比 ArrayList慢，是因为vector本身是同步的.
	 * 2、采用双向队列。提供优化的顺序访问性能，同时可以高效的在列表中部进行插入和删除操作。
	 */
	public static void main(String[] args) {
		List<String> lis = new LinkedList<String>();

		lis.add("abc");
		System.out.println((lis).get(0).length());
		System.out.println(lis.get(0).length());

		ArrayList<Integer> al = new ArrayList<Integer>();
		Vector<Integer> vc = new Vector<Integer>();
		LinkedList<Integer> ll = new LinkedList<Integer>();

		al.add(100);
		al.add(8);
		al.add(9);
		al.add(90);
		al.add(900);
		al.add(null);

		ll.add(100);
		ll.add(8);
		ll.add(9);
		ll.offer(90);
		ll.offer(900);
		ll.offer(null);

		vc.add(100);
		vc.add(8);
		vc.add(9);
		vc.add(90);
		vc.add(900);
		vc.add(null);

		System.out.println(al);
		System.out.println(ll);
		System.out.println(vc);

	}

}
