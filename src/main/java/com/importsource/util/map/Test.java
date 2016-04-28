package com.importsource.util.map;

import java.util.HashMap;
import java.util.Map;

public class Test {
	public static void main(String[] args) {
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("apple", "新鲜的苹果");      //向列表中添加数据
	    map.put("computer", "配置优良的计算机");    //向列表中添加数据
	    map.put("book", "堆积成山的图书");      //向列表中添加数据
	    System.out.println("Map集合大小为："+map.size());
	    map.remove("apple");
	    map.remove("book");
	    System.out.println("移除内容后的Map集合大小为："+map.size());
	}
}
