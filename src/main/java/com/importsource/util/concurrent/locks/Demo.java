package com.importsource.util.concurrent.locks;


public class Demo {

	public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
        	new Thread(new Worker()).start();//线程启动了
		}
        
        System.out.println(X.count);
	}
	
	static class Worker implements Runnable {

		public void run() {
			boolean flag=true;
			X x=new X();
			if(x.count==0){
				flag=false;
			}
			while(flag){
				x.m();
			}
		
		}
	}
	
	

}
