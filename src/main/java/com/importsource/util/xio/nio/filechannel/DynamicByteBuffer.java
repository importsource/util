package com.importsource.util.xio.nio.filechannel;

/**
 * 动态扩展的buffer
 * @author Hezf
 *
 */
public class DynamicByteBuffer {
    private  byte[] dbb;
    
    private static DynamicByteBuffer instance;
    
    private DynamicByteBuffer(byte[] dbb){
    	this.dbb=dbb;
    }
    public static DynamicByteBuffer allocate(int capacity){
    	instance= new DynamicByteBuffer(new byte[capacity]);
    	return instance;
    }
    
    
    
    
    public static byte[] array(){
    	return instance.dbb;
    }
}
