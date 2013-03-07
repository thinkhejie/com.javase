package queue.test;

import java.util.Vector;

public class WorldOfCow {
	int current_year;
	Vector cows = null;

	public WorldOfCow() {
		//这个世界诞生了，但什么都没有 
		//不知道现在是哪一年 current_year 
		//只有一个牛栏的标牌 cows 
	}

	public void init() {
		current_year = 2006; //上帝说今年是2006年 
		cows = new Vector(); //上帝说要有一个空的牛栏 
		cows.addElement(new Cow(0)); //上帝创造了一头母牛放进牛栏，它。。。居然是公元0年出生的 
	}

	public void spend() {
		//度过一年 
		current_year++; //年份增加了 
		for (int i = 0; i < cows.size(); i++) {
			//让我们来看看牛栏 
			Cow cow = (Cow) cows.elementAt(i); //找出一头母牛 
			if (cow.bred(current_year)) //她今年生小牛了吗？ 
				cows.addElement(new Cow(current_year)); //生了的话就把小牛也关进牛栏 
		}
	}

	public int getQuantityOfWorld() {
		//数数现在牛栏里有几头牛？ 
		return cows.size();
	}

	public static void main(String[] argv) {
		//		Integer i = new Integer(1);
		//		byte b = i.byteValue();
		//		new Byte(b);
		//		//Byte.SIZE;
		WorldOfCow world = new WorldOfCow(); //上帝创造了一个新的世界 
		world.init(); //上帝对这个世界进行了初始化 
		for (int i = 0; i < 20; i++) {
			world.spend(); //这个世界度过了20年
		}
		System.out.println("20年过去了，共有 " + world.getQuantityOfWorld() + "头母牛生活在这个大陆上 ");
	}
}
