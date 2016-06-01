package com.importsource.util.concurrent.countdownlatch;


/**
 * 示例：普通线程的使用举例
 * 
 * @author hezf
 */
public class NormalThreadDemo {
	private static final int N = 10;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("[主线程]开始做事情：");
		for (int i = 1; i <= N; i++) {
			new Thread(new Worker(i)).start();// 线程启动了
		}
		System.out.println("[主线程]ok！所有事情完成！");
	}

	static class Worker implements Runnable {
		private int index;

		Worker(int index) {
			this.index = index;
		}

		public void run() {
			System.out.println("[子线程]做第"+index+"件事情！");
		}
	}
}
