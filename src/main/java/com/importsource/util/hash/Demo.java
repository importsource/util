package com.importsource.util.hash;

public class Demo {

	public static void main(String[] args) {
		
		System.out.println(hashCode("sdfsdf".toCharArray()));
		
		System.out.println(hashCode("sdfsdf".toCharArray()));
		
		System.out.println(s(23, 'c'));
		
		System.out.println('c'+1);
		
		
		Demo demo=new Demo();
		System.out.println(demo.hashCode()); 
        
	}
	
	  /** Cache the hash code for the string */
    private static int hash; // Default to 0
	public static int hashCode(char[] value) {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }
	
	public static int s(int h,char val){
		return   31 * h + val;
	}

}
