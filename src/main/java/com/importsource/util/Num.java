package com.importsource.util;

public class Num extends Token{  
    public final int value;  
      
    public Num(int v) {  
        super(Tag.NUM);  
        this.value = v;  
    }  
      
    public String toString() {  
        return  "" + value;  
    }  
}  