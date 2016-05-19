package com.importsource.util.collection.vector;

import java.util.ArrayList;
import java.util.Vector;

/**
 * 要记住vector是个古老的东西。为什么你很少用。
 * 
 * 那是因为vector是个基本上被淘汰了的东西。
 * 
 * 我们可以这样认为。
 * 
 * 
 * @author Hezf
 *
 */
public class Demo {

	public static void main(String[] args) {
		Vector<String> vector=new Vector<String>();
		vector.add("sdfsdf");
		vector.add("sdfsdf");
		vector.add("sdfsdf");
		
		for (int i = 0; i < vector.size(); i++) {
			System.out.println(vector.get(i));
		}
		
		
		
		
		

	}

}
