package com.importsource.util.instanceof1;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test {
	public static <T> String  say(Class<T> type){
		return type.getSimpleName();
	}

	public static void main(String[] args) {
		System.out.println(say(List.class));
		System.out.println(say(Set.class));
		System.out.println(say(Map.class));

	}

}
