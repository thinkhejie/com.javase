package com.java.aop;

import java.io.File;

/**
 * 
 * @author hejie
 * 
 */
public class DataGenerator
{
	public static File	execlFile;

	public static void initCache(String path)
	{
		execlFile = new File(path);
	}
}
