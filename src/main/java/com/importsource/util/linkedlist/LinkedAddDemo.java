package com.importsource.util.linkedlist;

import java.util.LinkedList;
import java.util.List;

public class LinkedAddDemo {

	public static void main(String[] args) {
		List<String> linkedList=new LinkedList<String>();
		
        long lStart=System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			linkedList.add(""+i);
		}
		
		long lEnd=System.currentTimeMillis();
		System.out.println("linkedlist添加用时："+(lEnd-lStart));
		
		
		
		
		
		long lStart2=System.currentTimeMillis();
		for(int i=0;i<linkedList.size();i++){
			System.out.println(linkedList.get(i));
		}
		long lEnd2=System.currentTimeMillis();
		System.out.println("linkedlist查询用时："+(lEnd2-lStart2));
		
		
		
		
	}

}
