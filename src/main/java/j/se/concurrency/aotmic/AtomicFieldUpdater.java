package j.se.concurrency.aotmic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicFieldUpdater {

	public static void main(String[] args) throws Exception {
		AtomicReferenceFieldUpdater<Node, String> updater = AtomicReferenceFieldUpdater.newUpdater(Node.class, String.class, "filed");
		Node node = new Node();
		updater.compareAndSet(node, node.filed, "test");
		System.out.println(node.filed);
		updater.compareAndSet(node, "test", "test8");
		System.out.println(node.filed);
	}

	static class Node {
		volatile String filed;
	}
}
