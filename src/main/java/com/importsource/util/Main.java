package com.importsource.util;
/* 
 * 主程序 
 */  
import java.io.*;  
  
public class Main {  
    public static void main(String[] args) throws IOException {  
        Lexer lexer = new Lexer();  
          
        while (lexer.getReaderState() == false) {  
            lexer.scan();  
        }  
          
        /* 保存相关信息 */  
        lexer.saveTokens();  
        lexer.saveSymbolsTable();  
          
    }  
}  