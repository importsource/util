package com.importsource.util.concurrent.countdownlatch;


/**
 * 示例：CountDownLatch的使用举例
 * 
 * @author hezf
 */
public class NormalThreadDemo {
	private static final int N = 10;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("start");
		for (int i = 1; i <= N; i++) {
			new Thread(new Worker(i)).start();// 线程启动了
		}
		System.out.println("end");
	}

	static class Worker implements Runnable {
		private int index;

		Worker(int index) {
			this.index = index;
		}

		public void run() {
			System.out.println("sdfsdfsdf"+index);
		}
	}
}
