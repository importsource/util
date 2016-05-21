package com.importsource.util.stacktrace;

/**
 * 
 * @author Hezf
 *
 */
public class C {
	private B b = new B();

	public void p() {
		System.out.println(StackTrace.trace(new Throwable()));
		b.p();
	}
}
