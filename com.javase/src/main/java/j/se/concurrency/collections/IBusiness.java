package j.se.concurrency.collections;

import java.util.concurrent.BlockingQueue;

public interface IBusiness<A, V> {

	V doWork(A a);

	BlockingQueue<A> makeData(int cpacity);
}
