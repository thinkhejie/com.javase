package com.generic;

import java.util.List;

public class JavaGene {

	public void addSuperList(List<? super Pane> param) {
		Pane p = new Pane();
		MyPane myPane = new MyPane();
		param.add(p);
		param.add(myPane);
	}

	public void addExtendList(List<? extends Pane> param) {
		Pane p = new Pane();
		MyPane myPane = new MyPane();
		java.lang.Object o = new java.lang.Object();
		// param.add(o);
		// param.add(myPane);
		param.add(null);
	}
}
