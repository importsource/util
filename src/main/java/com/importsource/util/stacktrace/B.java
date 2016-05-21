package com.importsource.util.stacktrace;

/**
 * 
 * @author Hezf
 *
 */
public class B {
	private A a = new A();

	public void p() {
		System.out.println(StackTrace.trace(new Throwable()));
		a.p();
	}
}
