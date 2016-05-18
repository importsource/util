package com.importsource.util.map1;


public class MapDemo {
    public static void main(String[] args){
    	Map<String, String> map=new DefautMap<String, String>();
    	map.put("1","hahahah");
    	map.put("2","hahahah2222");
    	map.put("3","hahahah222");
    	
    	
    	System.out.println(map.get("1"));
    	System.out.println(map.get("2"));
    	System.out.println(map.get("3"));
    	
    	
    }
}
