package com.importsource.util.retry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Demo {

	private static final long TIMEOUT = 300;

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newCachedThreadPool();
		// Create a task that returns a String
		Callable<String> task1 = new Callable<String>() {
			public String call() {
				// Do stuff here
				System.out.println("hahah");
				return "hahahah";
			}
		};
		// Make it try up to three times
		RetriableTask<String> retriable1 = new RetriableTask<String>(task1, 3, null);

		Collection<Callable<String>> tasks = new ArrayList<Callable<String>>();
		tasks.add(retriable1);
		// TIMEOUT == timeout in seconds
		List<Future<String>> results = pool.invokeAll(tasks, TIMEOUT, TimeUnit.SECONDS);

		for (Future<String> result : results) {
			// Un-retried exceptions will pop out here
			String returnValue = result.get();
		}
	}

}
