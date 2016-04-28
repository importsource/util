package com.importsource.util.aop1;
public interface Student {
	@AOP(value="com.importsource.util.aop1.StudentAspectTwo")
	public void sayHello();
	@AOP(value="com.importsource.util.aop1.StudentAspectTwo")
	public void sayHi();
}
