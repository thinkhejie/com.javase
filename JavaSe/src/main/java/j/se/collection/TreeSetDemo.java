package j.se.collection;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/*TreeSet中通过实现Comparable建立有序容*/
public class TreeSetDemo {
	public static void main(String[] args) {
		Set<Person> set = new TreeSet<Person>();
		set.add(new Person("zeng", 26));
		set.add(new Person("wangshuai", 24));
		set.add(new Person("liyang", 25));
		set.add(new Person("tengfeng", 21));
		set.add(new Person("liyang", 26));
		Iterator<Person> it = set.iterator();
		while (it.hasNext()) {
			Person str = it.next();
			System.out.println(str);
		}
		int size = 1;
		int size2 = 2;
		int size3 = 3;
		System.out.println(size >> 1);//右移
		System.out.println(size2 >> 1);//右移
		System.out.println(size3 >> 1);//右移
	}
}
