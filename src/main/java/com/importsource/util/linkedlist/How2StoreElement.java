package com.importsource.util.linkedlist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class How2StoreElement {

	public static void main(String[] args) {
		List<String> linkedList = new LinkedList<String>();
		linkedList.add("nihao");
		linkedList.add("niha1");
		linkedList.add("niha2");
		linkedList.add("niha3");
		linkedList.add("niha4");
		linkedList.add("niha5");
		
		
		for (int i=0;i<linkedList.size();i++) {
			String string = linkedList.get(i);
			System.out.println(string);
		}

	}

}
