package com.importsource.util.stacktrace;

/**
 * 用于跟踪方法调用
 * @author Hezf
 *
 */
public class StackTrace {
	public static String trace(Throwable throwable) {
		if(throwable==null){
			throw new NullPointerException("Throwable can't be null.");
		}
		StackTraceElement[] stackTraces = throwable.getStackTrace();
		StringBuilder from = new StringBuilder();
		from.append("depth:");
		from.append(stackTraces.length-1);
		from.append(",");
		for (int i = stackTraces.length - 1; i >= 0; i--) {
			StackTraceElement element = stackTraces[i];
			String clazzName = element.getClassName();
			if(i==stackTraces.length - 1){
				try {
					from.append(",");
					from.append(Class.forName(clazzName).newInstance().toString());
					from.append(",");
				} catch (InstantiationException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (ClassNotFoundException e) {
					throw new RuntimeException(e);
				}
			}
			
			from.append(clazzName);
			from.append(">>>");

		}
		return from.toString();
	}

}
