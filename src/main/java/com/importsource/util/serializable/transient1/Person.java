package com.importsource.util.serializable.transient1;
import java.io.*;

/**
 * 需要序列化的类
 * @author Hezf
 *
 */
class Person implements Serializable 
{
  // name 被序列化
  private String name;
  // age 不会被序列化
  private transient int  age;
  // country作为静态变量不会被序列化，但这个值会存在。
  static String country = "cn";
  private String country1="default";
  Person(String name,int age,String country,String country1)
  {
    this.name = name;
    this.age = age;
    this.country = country;  
    this.country1=country1;
  }
  
  public String toString()
  {
    return name+":"+age+":"+country+":"+country1;
  }

}