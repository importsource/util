package com.importsource.util.hash;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
 
 
class Person{
    private String name;
    private int age;
     
    public Person(String name,int age) {
        this.name = name;
        this.age = age;
    }  
     
    public void setAge(int age){
        this.age = age;
    }
         
    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Person)obj).name) && this.age== ((Person)obj).age;
    }
}
 
public class Main {
 
    public static void main(String[] args) {
         
    	Person p1 = new Person("Hezhuofan", 29);
        System.out.println(p1.hashCode());
             
        HashMap<Person, Integer> hashMap = new HashMap<Person, Integer>();
        hashMap.put(p1, 1);
         
        System.out.println(hashMap.get(new Person("Hezhuofan", 29)));
    }
}