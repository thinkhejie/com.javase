package com.java.aop;

import java.io.File;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataGeneratorJUnit4ClassRunner.class)
@DataGeneratorConfig(excelFiles = "d:\\固话捆绑宽带_example.xls")
public class TestData
{
	@Test
	public void dataTest()
	{
		File file = DataGenerator.execlFile;
		System.out.println(file.getName());
	}
}
