package j.se.concurrency.aotmic;

public class Pair<K, V> {
	public K first;

	public V second;

	public Pair() {
	}

	public Pair(K first, V second) {
		this.first = first;
		this.second = second;
	}
}
