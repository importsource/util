package com.importsource.util.mr;

import java.util.List;

public interface Reducer {
	public void reduce(String key, List<Object> values) ;
}
