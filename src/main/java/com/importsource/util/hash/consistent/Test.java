package com.importsource.util.hash.consistent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.importsource.util.hash.consistent.ConsistentHash.HashFunction;

public class Test {

	public static void main(String[] args) {
		HashSet< String> set = new HashSet< String>();  
        set.add( "A" );  
        set.add( "B" );  
        set.add( "C" );  
        set.add( "D" );  

        Map< String, Integer> map = new HashMap< String, Integer>();  

        ConsistentHash< String> consistentHash = new ConsistentHash<String>( new HashFunction(), 1000, set);  

         int count = 10000;  

         for (int i = 0; i < count; i++) {  
               String key = consistentHash.get(i);  
               if (map.containsKey(key)) {  
                    map.put(consistentHash.get(i), map.get(key) + 1);  
              } else {  
                    map.put(consistentHash.get(i), 1);  
              }  
               // System.out.println(key);  
        }  

        consistentHash.showServer(map);  
        map.clear();  
        consistentHash.remove( "A" );  

        System. out .println("------- remove A" );  

         for (int i = 0; i < count; i++) {  
               String key = consistentHash.get(i);  
               if (map.containsKey(key)) {  
                    map.put(consistentHash.get(i), map.get(key) + 1);  
              } else {  
                    map.put(consistentHash.get(i), 1);  
              }  
               // System.out.println(key);  
        }  

         consistentHash.showServer(map);  
        map.clear();  
        consistentHash.add( "E" );  
        System. out .println("------- add E" );  

         for (int i = 0; i < count; i++) {  
               String key = consistentHash.get(i);  
               if (map.containsKey(key)) {  
                    map.put(consistentHash.get(i), map.get(key) + 1);  
              } else {  
                    map.put(consistentHash.get(i), 1);  
              }  
               // System.out.println(key);  
        }  

         consistentHash.showServer(map);  
        map.clear();  

        consistentHash.add( "F" );  
        System. out .println("------- add F服务器  业务量加倍" );  
        count = count * 2;  
         for (int i = 0; i < count; i++) {  
              String key = consistentHash.get(i);  
               if (map.containsKey(key)) {  
                    map.put(consistentHash.get(i), map.get(key) + 1);  
              } else {  
                    map.put(consistentHash.get(i), 1);  
              }  
               // System.out.println(key);  
        }  

         consistentHash.showServer(map); 

	}

}
