package org.go.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author hejie
 *
 */
public class ThreadPool extends ThreadPoolExecutor {
	//private boolean handoffPending = false;
	//private boolean inheritGroup = true;
	//private boolean inheritLoader = false;
	//private boolean isShutdown = false;
	//private final Object nextRunnableLock = new Object();
	private String id;
	private boolean isPaused;
	private ReentrantLock pauseLock = new ReentrantLock();
	private String schedInstId = null;
	private String schedName = null;
	private Condition unpaused = pauseLock.newCondition();

	public ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	public ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeUnit, BlockingQueue<Runnable> queue, RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, queue, handler);
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		pauseLock.lock();
		try {
			while (isPaused)
				unpaused.await();
		} catch (InterruptedException ie) {
			t.interrupt();
		} finally {
			pauseLock.unlock();
		}
	}

	public int blockForAvailableThreads() {
		int active = super.getActiveCount();
		int max = super.getMaximumPoolSize();
		return (max - active);
	}

	@Override
	public void execute(Runnable runnable) {
		for (int i = 0; i < this.getCorePoolSize(); i++) {
			super.execute(runnable);
		}
	}

	public String getId() {
		return id;
	}

	public String getPoolId() {
		return null;
	}

	public String getSchedInstId() {
		return schedInstId;
	}

	public String getSchedName() {
		return schedName;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void pause() {
		pauseLock.lock();
		try {
			isPaused = true;
		} finally {
			pauseLock.unlock();
		}
	}

	public void resume() {
		pauseLock.lock();
		try {
			isPaused = false;
			unpaused.signalAll();
		} finally {
			pauseLock.unlock();
		}
	}

	public boolean runInThread(Runnable runnable) {
		if (runnable == null) {
			return false;
		}
		super.execute(runnable);
		return true;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setInstanceId(String schedInstId) {
		this.schedInstId = schedInstId;
	}

	public void setInstanceName(String schedName) {
		this.schedName = schedName;
	}

	public void setSchedInstId(String schedInstId) {
		this.schedInstId = schedInstId;
	}

	public void setSchedName(String schedName) {
		this.schedName = schedName;
	}

	public void shutdown(boolean waitForJobsToComplete) {
		if (waitForJobsToComplete) {
			super.shutdown();
		} else {
			super.shutdownNow();
		}
	}

	public void start(Runnable run) {
		super.execute(run);
	}
}
