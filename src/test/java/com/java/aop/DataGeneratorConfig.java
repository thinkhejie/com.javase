package com.java.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE })
public @interface DataGeneratorConfig {
	/**
	 * excel文件列表
	 * 
	 * @return
	 */
	String excelFiles();

}
