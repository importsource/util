package com.importsource.util.stacktrace;

/**
 * 
 * @author Hezf
 *
 */
public class Demo {
    private static String seqId="";
	public static void main(String[] args) {
		seqId = "234sd3ed2dXl1";
		C c = new C();
		c.p();
	}
	
	@Override
	public String toString(){
		return seqId;
	}

}
