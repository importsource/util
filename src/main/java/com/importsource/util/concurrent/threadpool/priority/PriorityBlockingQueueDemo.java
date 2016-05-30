package com.importsource.util.concurrent.threadpool.priority;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 优先级阻塞队列
 * @author Hezf
 *
 */
public class PriorityBlockingQueueDemo {

	public static void main(String args[]) {
		ExecutorService exec = Executors.newCachedThreadPool();
		PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();

		exec.execute(new PrioritizedTaskProducer(queue, exec));
		try {
			TimeUnit.MILLISECONDS.sleep(250);
		} catch (InterruptedException e) {
		}
		exec.execute(new PrioritizedTaskConsumer(queue));
	}
}