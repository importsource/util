package com.importsource.util.concurrent.locks;

public class X {
	private final ReentrantLock lock = new ReentrantLock();
    public static int count=100;
	public void m() {
		lock.lock(); // block until condition holds
		try {
			if(count==0){
				return ;
			}
            count=count-1;
		} finally {
			lock.unlock();
		}
	}
}