package com.importsource.util.final1;

/**
 * 我是重写了父类的子类
 * @author Hezf
 *
 */
public class Sub extends Super {
	/**
	 * 父类的方法重写后可以进行final
	 */
    @Override
	public final void hello(){
    	System.out.println("我是重写了父类的hello，然后final");
    }
}
