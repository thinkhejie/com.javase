/**
 *
 *设有5个哲学家，共享一张放有5把椅子的桌子，每人分得一把椅子，
 *但是，桌子上共有5只筷子，在每人两边各放一只，哲学家们在肚子饥饿
 *时才试图分两次从两边拿起筷子就餐。
 *条件:
 *1）拿到两只筷子时哲学家才开始吃饭。
 *2）如果筷子已在他人手上，则该哲学家必须等他人吃完之后才能拿到筷子。
 *3）任一哲学家在自己未拿到两只筷子前却不放下自己手中的筷子。
 *要求:
 *1）描述一 个保证不会出现两个邻座同时要求吃饭的通信算法。
 *2）描述一个即没有两个邻座同时吃饭，又没有饿死（永远拿不到筷子）的算法
 *主要算法实现:
 *1) 使奇数项的哲学家从左手先拿筷子,然后再拿右手的筷子。
 *   偶数项的哲学家就先拿右手的,再拿左手的。
 */
package j.se.base;

public class EatingThread extends Thread {
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			KuaiZi kz1 = new KuaiZi("筷子1", true);
			KuaiZi kz2 = new KuaiZi("筷子2", true);
			KuaiZi kz3 = new KuaiZi("筷子3", true);
			KuaiZi kz4 = new KuaiZi("筷子4", true);
			KuaiZi kz5 = new KuaiZi("筷子5", true);
			Philosopher pa = new Philosopher("a", "a", kz5, kz1);
			Philosopher pb = new Philosopher("b", "b", kz1, kz2);
			Philosopher pc = new Philosopher("c", "c", kz2, kz3);
			Philosopher pd = new Philosopher("d", "d", kz3, kz4);
			Philosopher pe = new Philosopher("e", "e", kz4, kz5);
			Thread t1 = pa;
			t1.setName("a");
			Thread t2 = pb;
			t2.setName("b");
			Thread t3 = pc;
			t3.setName("c");
			Thread t4 = pd;
			t4.setName("d");
			Thread t5 = pe;
			t5.setName("e");
			t1.start();
			t2.start();
			t3.start();
			t4.start();
			t5.start();
		}
	}
}
