package arithmetic;

import java.util.LinkedHashMap;

public class ManyMap {

	/**
	 *   注意，此实现不是同步的。
	 */
	public static void main(String[] args) {

		LinkedHashMap<Integer, String> copy = new LinkedHashMap<Integer, String>();
		copy.put(8, "eeeee");
		copy.put(5, "eeeee");
		copy.put(1, "value");
		copy.put(2, "value");
		copy.put(3, "ccccc");
		copy.put(4, "ddddd");
		System.out.println(copy);

		LinkedHashMap<String, String> res = new LinkedHashMap<String, String>();
		res.put("z", "eeeee");
		res.put("Z", "eeeee");
		res.put("b", "value");
		res.put("B", "value");
		res.put("c", "ccccc");
		res.put("C", "ddddd");
		System.out.println(res);
	}

}
