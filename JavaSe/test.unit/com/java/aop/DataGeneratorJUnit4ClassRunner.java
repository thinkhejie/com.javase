package com.java.aop;

import org.junit.internal.runners.InitializationError;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.notification.RunNotifier;

/**
 * 
 * @author hejie
 * 
 */
public class DataGeneratorJUnit4ClassRunner extends JUnit4ClassRunner
{

	public DataGeneratorJUnit4ClassRunner(Class<?> klass) throws InitializationError
	{
		super(klass);
	}

	@Override
	public void run(final RunNotifier notifier)
	{
		// 在运行前对DataGenerator进行初始化
		initGenerator();
		super.run(notifier);
	}

	/**
	 * 初始化DataGenerator
	 */
	private final void initGenerator()
	{
		Class<?> clazz = getTestClass().getJavaClass();
		while (clazz != null)
		{
			DataGeneratorConfig annotation = clazz.getAnnotation(DataGeneratorConfig.class);

			if (annotation != null)
			{
				String excelFile = annotation.excelFiles();
				try
				{
					// DataGenerator.initCache(getAbsoluteExcelPaths(excelFile));
					DataGenerator.initCache(excelFile);
				}
				catch (Exception e)
				{
					throw new RuntimeException("使用注解初始化DataGenerator失败", e);
				}
				break;
			}
			clazz = clazz.getSuperclass();
		}
	}

	/**
	 * 取得excel文件绝对路径
	 * 
	 * @param excelPaths
	 * @return
	 */
	private String getAbsoluteExcelPaths(String excelPath)
	{
		String realPaths = getAbsolutePath(excelPath);
		return realPaths;
	}

	/**
	 * 根据文件名取得文件绝对路径
	 * 
	 * @param fileName
	 * @return
	 */
	private String getAbsolutePath(String fileName)
	{
		// ClassLoader java =
		// DataGeneratorJUnit4ClassRunner.class.getClassLoader();
		String kk = ClassLoader.getSystemResource(fileName).getFile();
		// String kk = java.getResource(fileName).getFile();
		return kk;
	}
}
