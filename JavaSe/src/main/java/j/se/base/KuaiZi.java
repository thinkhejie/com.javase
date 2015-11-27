package j.se.base;

public class KuaiZi {
	String name;
	boolean enbled;

	public KuaiZi() {

	}

	public KuaiZi(String name, boolean enbled) {
		this.name = name;
		this.enbled = enbled;
	}

	public boolean changeStatus(KuaiZi kz, String strStatus) {
		if (strStatus.equals("T")) {
			kz.enbled = true;
			return true;
		} else if (strStatus.equals("F")) {
			kz.enbled = false;
			return true;
		} else
			return false;
	}

	public synchronized void pickup() {
		enbled = false;
	}

	public synchronized void putdown() {
		enbled = true;
	}
}
