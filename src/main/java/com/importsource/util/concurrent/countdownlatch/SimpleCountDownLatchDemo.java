package com.importsource.util.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 示例：CountDownLatch的使用举例
 * 
 * @author hezf
 */
public class SimpleCountDownLatchDemo {
	private static final int N = 10;

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch doneSignal = new CountDownLatch(N);
		CountDownLatch startSignal = new CountDownLatch(1);// 开始执行信号

		for (int i = 1; i <= N; i++) {
			new Thread(new Worker(i,doneSignal, startSignal)).start();// 线程启动了
		}
		
		System.out.println("[主线程]我：你们去上厕所吧，貌似只有一个坑，排好队一个个来，我在这等你们：");
		startSignal.countDown();// 开始执行啦
		
		
		doneSignal.await();// 等待所有的线程执行完毕
		System.out.println("[主线程]还有："+doneSignal.getCount()+"个人在排队");
		System.out.println("[主线程]我：ok！都上完了，我们就走吧！");

	}

	static class Worker implements Runnable {
		private final CountDownLatch doneSignal;
		private final CountDownLatch startSignal;
		private int index;

		Worker(int index, CountDownLatch doneSignal, CountDownLatch startSignal) {
			this.index = index;
			this.startSignal = startSignal;
			this.doneSignal = doneSignal;
		}

		public void run() {
			try {
				startSignal.await(); // 等待开始执行信号的发布
				System.out.println("[子线程]第"+index+"位小伙伴去上厕所了！还有："+(doneSignal.getCount()-1)+"个人在外面排队,开始信号的实时count："+startSignal.getCount());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				doneSignal.countDown();
			}
		}
	}
}
