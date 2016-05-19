package com.importsource.util.linkedlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayAddDemo {

	public static void main(String[] args) {
		
		
		
		
        List<String> arrayList=new ArrayList<String>();
		
        long lStart1=System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			arrayList.add(""+i);
		}
		
		long lEnd1=System.currentTimeMillis();
		System.out.println("arraylist添加用时："+(lEnd1-lStart1));
		
		System.out.println("==============================================================");
		
		
		
		long lStart3=System.currentTimeMillis();
		for(int i=0;i<arrayList.size();i++){
			System.out.println(arrayList.get(i));
		}
		long lEnd3=System.currentTimeMillis();
		System.out.println("arrayList查询用时："+(lEnd3-lStart3));
		
		
		
	}

}
