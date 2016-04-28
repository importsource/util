package com.importsource.util.dsql;

import java.io.*;  
  
/**   
 * #{not_nvl(       type,   ' AND entity_type = '+bind('type')    )}
 * 
 * # 符号
 * { 符号
 * } 符号
 * @author Hezf
 *
 */
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