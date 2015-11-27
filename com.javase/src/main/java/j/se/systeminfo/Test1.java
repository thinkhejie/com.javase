package j.se.systeminfo;

import java.util.HashMap;

import org.junit.Test;

public class Test1 {
	@Test
	public void main1() {
		//CPU个数
		int i = Runtime.getRuntime().availableProcessors();
		System.out.println(i);
	}

	@Test
	public void main() {
		try {
			//Dady d = new Dady();
			//d.setA(55);
			//Map map = new HashMap();
			//map.put("A", "b");
			//Map m;
			//Dady d1 = (Dady) BeanUtils.cloneBean(d);
			//BeanUtils.copyProperties(map, m);
			HashMap map = new HashMap();
			map.put("A", "b");
			HashMap map1 = (HashMap) map.clone();
			System.out.println(map1.hashCode());
			System.out.println(map.hashCode());
			System.out.println(map == map1);
			System.out.println(map.equals(map1));
			System.out.println(map1.get("A"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
