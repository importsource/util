package com.importsource.util.classloader;

public class TinyLogicClassLoader extends ClassLoader {
	@Override
	 protected Class<?> findClass(String name) throws ClassNotFoundException {
		    
	        throw new ClassNotFoundException(name);
	 }
}
