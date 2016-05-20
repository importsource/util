package com.importsource.util.linkedlist.custom;

public class Demo {
	public static void main(String[] args) {
		List<String> lst=new LinkedList<String>();
		lst.add("nihao1");
		lst.add("niha2");
		lst.add("niha3");
		
		lst.add("nihao4");
		lst.add("nihao5");
		lst.add("nihao6");
		
		lst.add("nihao7");
		lst.add("nihao8");
		lst.add("nihao9");
		
		lst.add("nihao10");
		lst.add("nihao11");
		lst.add("nihao12");
		
		for(int i=0;i<lst.size();i++){
			System.out.println(lst.get(i));
		}
		
		System.out.println(lst.size());
	}
}
