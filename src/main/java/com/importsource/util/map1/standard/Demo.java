package com.importsource.util.map1.standard;

import java.util.HashMap;
import java.util.Map;


public class Demo {

	public static void main(String[] args){
		Map<String, String> map=new HashMap<String,String>();
		map.put("1", "s");
		map.put("1", "sss");
		System.out.println(map.get("1"));
	}

}
