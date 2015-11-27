package cn.itcast.day2;

import java.util.Set;

//dao data access object--->crud
public class GenericDao<T> {
	public void add(T x) {

	}

	public T findById(int id) {
		return null;
	}

	public void delete(T obj) {

	}

	public void delete(int id) {

	}

	public void update(T obj) {

	}

	public static <E> void update2(E obj) {

	}

	public T findByUserName(String name) {
		return null;
	}

	public Set<T> findByConditions(String where) {
		return null;
	}
}
