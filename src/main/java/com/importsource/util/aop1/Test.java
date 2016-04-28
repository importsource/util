package com.importsource.util.aop1;

/**
 * 
 * @author Hezf
 * 
 * <h2>轻量级的aop切面框架。</h2>
 * <p>
 * 1、支持给指定类的指定方法加入逻辑。逻辑支持方法前和方法后或方法前后。
 * <p>
 * 2、使用方法。我通过给某个类的某个方法加一个注解。一切就okay了。
 *
 */
public class Test {

	public static void main(String[] args) {
		Student s = (Student)DynamicProxyFactory.newInstance(new StudentImpl());
		s.sayHello();
		s.sayHi();
	}

}

