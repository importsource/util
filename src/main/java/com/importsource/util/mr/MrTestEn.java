package com.importsource.util.mr;

/**
 * 写一个框架。先从api写起。
 * 
 * @author Hezf
 *
 */
public class MrTestEn {
	public static void main(String[] args) {
		Application app = new Application();
		Mapper mapper = new Map();
		app.setMapper(mapper);
		Reducer reducer = new ReducerImpl();
		app.setReducer(reducer);
		ApplicationManager.start(app);
	}
}
