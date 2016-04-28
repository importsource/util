package com.importsource.util.mr;


/**
 * Java实现逆向最大匹配中文分词算法
 * @author Hezf
 *
 */
public class SplitChineseCharacter {
	public static void main(String[] args) {
		String input = "我的宝宝是个漂亮的妹子"; // 要匹配的字符串
		Split split=new Split();
		split.setDictionary();
		split.split(input);
		/*String[] dic=split.getDictionary();
		for(int i=0;i<dic.length;i++){
			System.out.println(dic[i]);
		}*/
	}
}

