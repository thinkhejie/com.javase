package arithmetic;

import java.util.Iterator;
import java.util.TreeSet;

public class LeanTreeSet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//TODO 自动生成方法存根
		TreeSet<Father> ts = new TreeSet<Father>();
		Father f1 = new Father();
		Father f2 = new Father();
		f1.name = "A";
		f2.name = "B";
		ts.add(f1);
		ts.add(f2);
		Iterator<Father> is = ts.iterator();
		while (is.hasNext()) {
			Father ff = is.next();
			System.out.println(ff.name);
		}
	}
}
