package j.se.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class Gerbil {

	/**
	 * @param args
	 */
	int gerbilNumber;
	int key;

	Gerbil() {

	}

	Gerbil(int key, int gerbilNumber) {
		this.key = key;
		this.gerbilNumber = gerbilNumber;
	}

	public static void main(String[] args) {
		Gerbil test = new Gerbil();
		Gerbil print;
		ArrayList<Gerbil> altest = test.createObject();
		/*
		 * for (int i = 0; i <= 9; i++) { print = (Gerbil) altest.get(i);
		 * test.hop(print); }
		 */

		Pattern ps = Pattern.compile("\\d");

		Iterator<Gerbil> it = altest.iterator();
		while (it.hasNext()) {
			print = it.next();
			test.hop(print);
		}
	}

	public void hop(Gerbil gb) {

		System.out.println(gb.gerbilNumber);
		System.out.println("  ");
	}

	public ArrayList<Gerbil> createObject() {
		ArrayList<Gerbil> al = new ArrayList<Gerbil>();
		for (int i = 0; i <= 9; i++) {
			Gerbil gb = new Gerbil(1, ExerciseUtil.getNumber());
			al.add(gb);
		}
		return al;

	}

	public Map<Integer, Gerbil> createMap() {
		Map<Integer, Gerbil> mp = new HashMap<Integer, Gerbil>();
		mp.put(0, new Gerbil(5, 43135));
		mp.put(1, new Gerbil(5, 33434));
		mp.put(2, new Gerbil(5, 16784));
		mp.put(3, new Gerbil(5, 95423));
		mp.put(4, new Gerbil(5, 53456));
		Set<Integer> st = mp.keySet();
		Iterator<Integer> it = st.iterator();
		while (it.hasNext()) {
			Gerbil newgb = mp.get((int) it.next());
			new Gerbil().hop(newgb);
		}
		return mp;

	}
}
