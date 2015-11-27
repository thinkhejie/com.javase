package j.se.base;

public class Philosopher extends Thread implements Runnable {
	String name;
	String number;
	KuaiZi leftKuaizi;
	KuaiZi rightKuaizi;

	Philosopher() {

	}

	Philosopher(String name, String number, KuaiZi leftKuaizi, KuaiZi rightKuaizi) {
		this.name = name;
		this.number = number;
		this.leftKuaizi = leftKuaizi;
		this.rightKuaizi = rightKuaizi;
	}

	public void pickup(KuaiZi kz) {
		kz.enbled = false;
	}

	public void putdown(KuaiZi kz) {
		kz.enbled = true;
	}

	public synchronized void eating() {
		if (leftKuaizi.enbled && rightKuaizi.enbled) {
			pickup(leftKuaizi);
			System.out.println(name + " 眼明手快，以迅雷不及掩耳之势一把抓起左手边的筷子：" + leftKuaizi.name);
			pickup(rightKuaizi);
			System.out.println(name + " 眼明手快，以迅雷不及掩耳之势一把抓起右手边的筷子 ：" + rightKuaizi.name);
			System.out.println(name + " 左右开弓，狼吞虎咽起来");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			putdown(leftKuaizi);
			putdown(rightKuaizi);
			System.out.println(name + " 酒足饭饱，打了个饱嗝，心满意足的放下了  " + leftKuaizi.name + " 和 " + rightKuaizi.name);
		} else {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		eating();
	}
}
