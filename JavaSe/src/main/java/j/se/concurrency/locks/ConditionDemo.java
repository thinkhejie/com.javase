package j.se.concurrency.locks;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 在上一篇中，我们明白了下J.U.C的锁的获取与释放的过程，这个过程重要经过在A.Q.S中保持一个等待队列来实现，此中我们也提到了，在A.Q.S中除一个等待队列以外，另有一个Condition队列，在了解Condition队列以前，先来看一下Condition是怎样回事：

The synchronizer framework provides a ConditionObject class for use by synchronizers that maintain exclusive synchronization and conform to the Lock interface. Any number of condition objects may be attached to a lock object, providing classic monitor-style await, signal, and signalAll operations, including those with timeouts, along with some inspection and monitoring methods.

下面的这一段内容摘自Doug Lea的AQS论文，从上面这一段话能够看出，Condition主如果为了在J.U.C框架中供给和Java古板的监督器气概的wait，notify和notifyAll方法近似的功用，那么先来诠释一下这三个要领的感化：

Object.wait()方法：使当前哨程开释Object上的监视器并且挂起，直到有别的的线程挪用Object.notify()方法或Object.notifyAll()方法唤醒以后线程，当被叫醒后，Object.wait()方法会测验考试从头获得看管器，胜利获取后连续往下履行。注重Object.wait()方法只要在当火线程持有Object的监督器的时刻才可以或许调用，否则会抛出非常。 
Object.notify()方法：用于唤醒另一个调用了Object.wait()方法的线程，如果有多个都调用了Object.wait()方法，那么就会挑选一个线程去notify()，详细选择哪个和具体的实现相关，当前线程在调用Object.notify()方法以后会就释放Object的监视器，和wait()方法同样，Object.notify()方法只有在当前线程只有Object的监视器的时候才干够调用，否则就会抛出反常。 
Object.notifyAll()方法：唤醒全部调用了Object.wait()方法的线程，如果有多个线程调用了Object.wait()方法，那么就会激发这些线程之间的合作，最终谁胜利获取到Object的监视器和具体的实现相关，当前线程在调用Object.notifyAll()方法以后会就释放Object的监视器，和wait()方法一样，Object.notifyAll()方法只有在当前线程只有Object的监视器的时候才气够调用，不然就会抛出异常。 
那么Condition是若何实现wait，notify和notifyAll方法的功能呢？我们接下来看：

在Condition中，wait，notify和notifyAll方法别离对应了await，signal和signalAll方法，固然Condition也供应了超时的、不成被中止的await()方法，不外咱们首要仍是看一看await，notify和notifyAll的实现，

 */
public class ConditionDemo {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(7);
		//final Business2 business = new Business2();
		final BoundedBuffer bb = new BoundedBuffer();
		for (int i = 0; i < 5; i++) {
			service.execute(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							bb.put(new Random().nextInt());
							Thread.sleep(100L);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
		for (int i = 0; i < 2; i++) {
			service.execute(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							System.out.println(bb.take());
							Thread.sleep(100L);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
	}
}
