package com.importsource.util.mr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Application extends AbstractMR {

	public void setMapper(Mapper mapper) {
		//Map m = new com.importsource.util.mr.Map();
		String str = readLine();
		
		String[] strArr = splitLine(str);

		for (int i = 0; i < strArr.length; i++) {
			//System.out.println(strArr[i]);
			mapper.map(strArr[i], 1);
		}

		Set<String> keySet = AbstractMR.map.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value =AbstractMR. map.get(key).toString();
			//System.out.println(key + ":" + value);
		}
		
	}

	public void setReducer(Reducer reducer) {
		Set<String> keySet = AbstractMR.map.keySet();
		Iterator<String> iterator1 = keySet.iterator();
		while (iterator1.hasNext()) {
			String key = (String) iterator1.next();
			String value =AbstractMR. map.get(key).toString();
			String[] valuesArr=value.split(",");
			
			List<Object> valuesList=new ArrayList<Object>();
			for(int i=0;i<valuesArr.length;i++){
				valuesList.add(valuesArr[i]);
			}
			reducer.reduce(key, valuesList);
		}
		
	}

}
