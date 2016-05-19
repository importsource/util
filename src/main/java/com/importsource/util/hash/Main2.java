package com.importsource.util.hash;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
 
 
class Person1{
    private String name;
    private int age;
     
    public Person1(String name,int age) {
        this.name = name;
        this.age = age;
    }  
     
    public void setAge(int age){
        this.age = age;
    }
         
    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Person1)obj).name) && this.age== ((Person1)obj).age;
    }
    
    @Override
    public int hashCode() {
        return name.hashCode()*37+age;
    }
}
 
public class Main2 {
 
    public static void main(String[] args) {
         
    	Person1 p1 = new Person1("Hezhuofan", 29);
        System.out.println(p1.hashCode());
             
        HashMap<Person1, Integer> hashMap = new HashMap<Person1, Integer>();
        hashMap.put(p1, 1);
         
        System.out.println(hashMap.get(new Person1("Hezhuofan", 29)));
    }
}