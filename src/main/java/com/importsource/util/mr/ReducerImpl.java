package com.importsource.util.mr;

import java.util.List;

public class ReducerImpl extends AbstractMR implements Reducer {
	 public void reduce(String key, List<Object> values) {
		 int sum=0;
		 for(int i=0;i<values.size();i++){
			sum+=Integer.parseInt(values.get(i).toString());
		 }
		 System.out.println(key+":"+sum);
	 }

	
}
