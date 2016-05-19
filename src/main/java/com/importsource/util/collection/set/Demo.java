
package com.importsource.util.collection.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * set不重复。这个要记住。
 * 
 * @author Hezf
 *
 */
public class Demo {
	public static void main(String[] args) {
	     Set<String> set=new HashSet<String>();
	     set.add("sdfsdf");
	     set.add("sdfsdf1");
	     for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			String item = (String) iterator.next();
			System.out.println(item);
		}
	}
}
