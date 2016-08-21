package com.importsource.util.hash.consistent;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHash<T> {  
  
       private final HashFunction hashFunction;  
       private final int numberOfReplicas;  
       private final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();  
  
       public ConsistentHash(HashFunction hashFunction, int numberOfReplicas, Collection<T> nodes) {  
             this .hashFunction = hashFunction;  
             this .numberOfReplicas = numberOfReplicas;  
  
             for (T node : nodes) {  
                  add(node);  
            }  
      }  
  
       public void add(T node) {  
             for (int i = 0; i < numberOfReplicas; i++) {  
                   circle .put(hashFunction .hash(node.toString() + i), node);  
            }  
      }  
  
       public void remove(T node) {  
             for (int i = 0; i < numberOfReplicas; i++) {  
                   circle .remove(hashFunction .hash(node.toString() + i));  
            }  
      }  
  
       public T get(Object key) {  
             if (circle .isEmpty()) {  
                   return null ;  
            }  
             int hash = hashFunction .hash(key);  
             // System.out.println("hash---: " + hash);  
             if (!circle .containsKey(hash)) {  
                  SortedMap<Integer, T> tailMap = circle .tailMap(hash);  
                  hash = tailMap.isEmpty() ? circle .firstKey() : tailMap.firstKey();  
            }  
             // System.out.println("hash---: " + hash);  
             return circle .get(hash);  
      }  
  
       public   static class HashFunction {  
             int hash(Object key) {
                   //md5加密后，hashcode
                   return MD5Encrypt.MD5Encode(key.toString()).hashCode();  
            }  
      }  
  
        
  
       public static void showServer(Map<String , Integer> map) {  
             for (Entry<String, Integer> m : map.entrySet()) {  
                  System. out .println("服务器 " + m.getKey() + "----" + m.getValue() + "个" );  
            }  
      }  
  
} 