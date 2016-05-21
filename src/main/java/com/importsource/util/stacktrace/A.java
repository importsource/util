package com.importsource.util.stacktrace;

/**
 * 
 * @author Hezf
 *
 */
public class A {
	public void p() {
		System.out.println(StackTrace.trace(new Throwable()));
		System.out.println("a");
	}

}
