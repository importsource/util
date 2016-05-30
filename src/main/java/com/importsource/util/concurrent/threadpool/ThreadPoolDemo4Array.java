package com.importsource.util.concurrent.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 要搞清楚线程池以及队列。
 * 
 * @author Hezf
 */
public class ThreadPoolDemo4Array {
	/**
	 * 具体的任务
	 * 
	 * @author Hezf
	 */
	static class MyJob implements Runnable {
		private String name;

		public MyJob(String name) {
			this.name = name;
		}

		/**
		 * 具体job内容
		 */
		public void run() {
			// 做点事情
			try {
				Thread.sleep(1000);

				System.out.println(name + " finished job!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// 创建线程池，为了更好的明白运行流程，增加了一些额外的代码
	    BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(2);
		//BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		// BlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();
		// BlockingQueue<Runnable> queue = new SynchronousQueue<Runnable>();

		// AbortPolicy/CallerRunsPolicy/DiscardOldestPolicy/DiscardPolicy

		/**
		 * 线程池的管理者。自己在设计api的时候也要注意什么时候叫mgr，什么时候叫executor。
		 */
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 5, TimeUnit.SECONDS, queue,
				new ThreadPoolExecutor.CallerRunsPolicy());

		// 向线程池里面扔任务
		for (int i = 0; i < 10; i++) {
			System.out.println("当前线程池大小[" + threadPool.getPoolSize() + "],当前队列大小[" + queue.size() + "]");

			threadPool.execute(new MyJob("Thread" + i));
		}
		// 关闭线程池
		threadPool.shutdown();
	}
}