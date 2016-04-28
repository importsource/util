package com.importsource.util.aop1;

import java.lang.reflect.Proxy;

/**
 * 代理工厂类
 * 
 * @author Hezf
 */
public class DynamicProxyFactory {
	
	/**
	 * 私有构造方法
	 */
	private DynamicProxyFactory() {}
	
	
	/**
	 * 工厂方法
	 * 
	 * @param instance 代理目标类实例对象
	 */
	public static Object newInstance(Object instance) {
		SimpleInvocationHandler hander = new SimpleInvocationHandler();
		hander.setSource(instance);
		return Proxy.newProxyInstance(instance.getClass().getClassLoader(), 
									  instance.getClass().getInterfaces(), 
									  hander);
	}
}