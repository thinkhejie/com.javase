package threaddemo.test;

import org.junit.Before;
import org.junit.Test;

public class Test7 {

	private int i;

	@Before
	public void before() {
		//i = 8;
	}

	@Test
	public void test1() {
		i = 6;
	}

	@Test
	public void test2() {
		System.out.println(i);
	}
}
