package com.importsource.util.concurrent.countdownlatch;


/**
 * 示例：普通线程的使用举例
 * 
 * @author hezf
 */
public class NormalThreadDemo {
	private static final int N = 10;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("[主线程]我：你们去上厕所吧，貌似只有一个坑，排好队一个个来，我在这等你们：");
		for (int i = 1; i <= N; i++) {
			new Thread(new Worker(i)).start();// 线程启动了
		}
		System.out.println("[主线程]我：ok！都上完了，我们就走吧！");
	}

	static class Worker implements Runnable {
		private int index;

		Worker(int index) {
			this.index = index;
		}

		public void run() {
			System.out.println("[子线程]第"+index+"位小伙伴去上厕所了");
		}
	}
}
