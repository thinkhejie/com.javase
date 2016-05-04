package cn.itcast.day2;

import org.junit.Test;

import j.se.base.GenericDao;

public class GenericDaoTest {
	@Test
	public void test() {
		GenericDao<String> dao = new GenericDao<String>();
		dao.findByUserName("");
	}
}
