package com.importsource.util.concurrent.threadpool.priority;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 使用PriorityBlockingQueue进行任务按优先级同步执行
 */
class PrioritizedTaskConsumer implements Runnable {
	private PriorityBlockingQueue<Runnable> q;

	public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> q) {
		this.q = q;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				q.take().run();
			}
		} catch (InterruptedException e) {
		}
		System.out.println("Finished PrioritizedTaskConsumer");
	}

}