package com.importsource.util.mr;

import java.util.Iterator;
import java.util.List;
import java.util.Set;



public  class MrTestCn {
	public static void main(String[] args) {
		
		// 读取wenzhang.txt内容。先把这个东西读到内存中。然后分词。
		Map m = new com.importsource.util.mr.Map();
		String str = m.readLine();
		Split split=new Split();
		split.setDictionary();
		List<String> splits=split.split(str);
		SeperatorOperation seperatorOperation=new SeperatorOperation(splits);
		str=seperatorOperation.operate(str);
		System.out.println(str);
		String[] strArr = m.splitLine(str);

		for (int i = 0; i < strArr.length; i++) {
			System.out.println(strArr[i]);
			m.map(strArr[i], 1);
		}

		Set<String> keySet = AbstractMR.map.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value =AbstractMR. map.get(key).toString();
			System.out.println(key + ":" + value);
		}
	}
}
