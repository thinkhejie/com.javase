package pingpang;

import java.util.LinkedHashMap;
import java.util.Map;

public class Test {
	private static Map<String, String> map1 = new LinkedHashMap<String, String>();
	private static Map<String, String> map2 = new LinkedHashMap<String, String>();
	private static Map<String, String> map3 = new LinkedHashMap<String, String>();
	private static int III;

	public Test(int k) {
		III = k;
	}

	static {
		//1
		map1.put("1", "郝猛");
		map1.put("2", "邱银虎");
		map1.put("3", "翟明明");
		map1.put("4", "葛一明");
		//map1.put("4", "林江辉");
		//2
		map2.put("1", "潘春龙");
		map2.put("2", "何杰");
		map2.put("3", "陈聪");
		map2.put("4", "墙翱");
		map2.put("5", "方杰");
		//
		//map1.put("4", "张灿");
		//map1.put("4", "范海波");
		//
		//
		//3
		map3.put("1", "赵洁");
		map3.put("2", "韩琴霞");
		//map3.put("3", "陈颖");
	}

	public static String getName(int a) {
		String str = "";
		if (III == 1) {
			str = map1.get(String.valueOf(a));
		} else if (III == 2) {
			str = map2.get(String.valueOf(a));
		} else if (III == 3) {
			str = map3.get(String.valueOf(a));
		}
		return str;
	}

	public static int getCount() {
		if (III == 1) {
			return map1.size();
		} else if (III == 2) {
			return map2.size();
		} else if (III == 3) {
			return map3.size();
		}
		return -1;
	}

	public void main() {
		int n, m;
		//Scanner cin = new Scanner(System.in);
		//System.out.print("输入队伍的数量: ");
		//n = cin.nextInt();
		n = getCount();
		System.out.println("队伍的数量:" + n);
		if (n % 2 == 0)
			m = n;
		else
			m = n + 1;
		int a = 1, b = 1, index = 1, loop = 0;
		for (int i = 1; i <= (m - 1) * (m / 2); i++) {
			if (a >= m)
				a = 1;
			if (index > m / 2)
				index = 1;
			if (index == 1) {
				loop++;
				if (i == 1) {
					b = m;
				} else {
					b = a;
				}
				System.out.println("第" + loop + "轮");
				if (((i - 1) / (m / 2)) % 2 == 0) {
					System.out.println(getName(a) + "--" + getName(m));
				} else {
					System.out.println(getName(m) + "--" + getName(a));
				}
			} else if (index > 1 && index <= m / 2) {
				if (b > 1)
					b--;
				else
					b = m - 1;
				System.out.println(getName(a) + "--" + getName(b));
			}
			index++;
			a++;
		}
	}
}
