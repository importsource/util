package com.importsource.util.mr;

import java.util.ArrayList;
import java.util.List;


public class SeperatorOperation {
	private List<String> words;

	public SeperatorOperation(List<String> words) {
		this.words = words;
	}

	public String operate(String content) {
		//String result = content;
		for (int i = 0; i < words.size(); i++) {
			String word = words.get(i);
				
				content=content.replace(word, ","+word+",");
			
		}
		content=content.replace(",,", ",");
		content=content.substring(1, content.length()-1);
		return content;
	}

	public String concat(String[] contentArr, String word) {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<contentArr.length;i++){
			if(i!=contentArr.length-1){
				sb.append(contentArr[i]);
				sb.append(",");
				sb.append(word);
				sb.append(",");
				
				
			}else{
				sb.append(contentArr[i]);
			}
		}
		return sb.toString();
	}
	
	
	
	public static void main(String[] args){
		List<String> words=new ArrayList<String>();
		words.add("妹子");
		words.add("的");
		words.add("漂亮");
		words.add("个");
		words.add("是");
		words.add("宝宝");
		words.add("我");
		SeperatorOperation seperatorOperation=new SeperatorOperation(words);
		String result=seperatorOperation.operate("我的宝宝是个漂亮的妹子");
		System.out.println(result);
		/*SeperatorOperation seperatorOperation=new SeperatorOperation(null);
		String[] contentArr=new String[]{"1","2","3"};
		String word="d";
		System.out.println(seperatorOperation.concat(contentArr, word));*/
		
	}
}
