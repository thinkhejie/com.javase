package cn.itcast.day2;

import org.junit.Test;

public class GenericDaoTest {
	@Test
	public void test() {
		GenericDao<String> dao = new GenericDao<String>();
		dao.findByUserName("");
	}
}
