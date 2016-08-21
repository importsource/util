/**
 * 
 */
package com.importsource.util.cache.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @author Hezf
 *
 */
public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CacheManager manager = CacheManager.create();
		// 通过manager可以生成指定名称的Cache对象
		Cache cache = cache = manager.getCache("demoCache");
		Element element=new Element("name", "hezhuofan");
		cache.put(element);
		System.out.println(cache.get("name"));
		// 使用manager移除指定名称的Cache对象
		manager.removeCache("demoCache");

	}

}
