package com.importsource.util.serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SerializeSetTest {

	 
	public static void main(String[] args) {
		String xx = new String("1"); 
		String yy = new String("2"); 
		List list = new ArrayList(); 
		list.add(xx);
		list.add(yy);
		Set set = new HashSet(list); 
		System.out.println(set.contains(xx)); 
		System.out.println(set.contains(yy)); 
	}
	
	

}
