package com.importsource.util.searchengin;  
  
import java.util.List; 
  
   
public class Test { 
  public static void main(String[] args) { 
    // TODO Auto-generated method stub  
    SerachBase serachBase = SerachBase.getSerachBase(); 
    serachBase.add("1", "你好！", "你好！"); 
    serachBase.add("2", "你好！我是张三。", "你好！我是张三。"); 
    serachBase.add("3", "今天的天气挺好的。", "今天的天气挺好的。"); 
    serachBase.add("4", "你是谁？", "你是谁？"); 
    serachBase.add("5", "高数这门学科很难", "高数确实很难。"); 
    serachBase.add("6", "测试", "上面的只是测试"); 
    String ids = serachBase.getIds("你的高数"); 
    System.out.println(ids); 
    List<Object> objs = serachBase.getObjects(ids); 
    if (objs != null) { 
      for (Object obj : objs) { 
        System.out.println((String) obj); 
      } 
    } 
  } 
  
} 