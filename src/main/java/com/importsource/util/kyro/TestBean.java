package com.importsource.util.kyro;

import java.io.Serializable;
import java.util.HashMap;

public class TestBean implements Serializable{  
	private static final long serialVersionUID = 1L;
		private int[] intArray;  
        private HashMap<String,String> hashMapVal;  
        private String strVal;  
        public int[] getIntArray () {  
            return intArray;  
        }  
        public void setIntArray (int[] intArray) {  
            this.intArray = intArray;  
        }  
        public HashMap<String, String> getHashMapVal () {  
            return hashMapVal;  
        }  
        public void setHashMapVal (HashMap<String, String> hashMapVal) {  
            this.hashMapVal = hashMapVal;  
        }  
        public String getStrVal () {  
            return strVal;  
        }  
        public void setStrVal (String strVal) {  
            this.strVal = strVal;  
        }  
    }  