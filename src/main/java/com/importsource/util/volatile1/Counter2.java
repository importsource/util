package com.importsource.util.volatile1;
/**
 * 
 * @author Hezf
 *
 */
public class Counter2 {
    public static volatile int count = 0;
    public static synchronized void inc() {
        //这里延迟1毫秒，使得结果明显
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }
        count++;
    }
 
    public static void main(String[] args) {
        //同时启动1000个线程，去进行i++计算，看看实际结果
 
        for (int i = 0; i < 1000; i++) {
        	
            new Thread(new Runnable() {
                public synchronized void run() {
                		Counter2.inc();
                }
            }).start();
        }
 
        //这里每次运行的值都有可能不同,可能为1000
        System.out.println("运行结果:Counter.count=" + Counter2.count);
    }
}