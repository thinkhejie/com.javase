package j.se.concurrency.locks;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import javax.xml.crypto.Data;

public class RWDictionary {
	private final Map<String, Data> m = new TreeMap<String, Data>();
	//1）创建ReentrantReadWriteLock对象
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final ReadLock r = rwl.readLock();//2) 抽取读锁
	private final WriteLock w = rwl.writeLock();//2) 抽取写锁

	public Data get(String key) {
		// 3) 对所有读者加锁
		r.lock();
		try {
			return m.get(key);
		} finally {
			r.unlock();
		}
	}

	public String[] allKeys() {
		// 3 ) 对所有读者加锁
		r.lock();
		try {
			return (String[]) m.keySet().toArray();
		} finally {
			r.unlock();
		}
	}

	public Data put(String key, Data value) {
		//  4) 对所有写者加锁
		w.lock();
		try {
			return m.put(key, value);
		} finally {
			w.unlock();
		}
	}

	public void clear() {
		//  4) 对所有写者加锁：
		w.lock();
		try {
			m.clear();
		} finally {
			w.unlock();
		}
	}

}
