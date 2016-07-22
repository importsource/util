package com.importsource.util.concurrent.locks.any.clh;


public class Demo {
    public static int remain=300;
	public static void main(String[] args) {
		for (int i = 1; i <= 100; i++) {
			new Thread(new Worker(i)).start();// 线程启动了
		}

	}
	
	static class Worker implements Runnable{
		
		private static Lock lock = new CLHLock();
		
		private int index;
		
		public Worker(int index){
			this.index=index;
		}
		public void run() {
			lock.lock(); // block until condition holds
			try {
	           System.out.println("sdfsdfsdf"+index);
			} finally {
				lock.unlock();
			}
		}
		
	}
}
