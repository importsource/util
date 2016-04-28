package com.importsource.util.io;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 
 * @author Hezf
 *
 */

public class MyReader {
	// 声明成员bufferedReader变量
	private BufferedReader bufferedReader;
	// 声明计数器
	private int count = 1;
	
	public int getLineNumber(){
		return  this.count;
	}

	public MyReader(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}

	// 封装readLine()方法
	public String readLine() {
		StringBuilder builder = new StringBuilder();
		try {
			String reader = bufferedReader.readLine();
			if (reader == null) {
				return null;
			}
			builder.append(count++).append(": ").append(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return builder.toString();
	}
}