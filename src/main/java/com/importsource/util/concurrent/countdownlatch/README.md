## CountDownLatch

### 1、什么是CountDownLatch？

A synchronization aid that allows one or more threads to wait until a set of operations being performed in other threads completes. 

它提供了一种同步机制，可以允许一个或多个线程等待直到其他的线程上的一系列操作完成。


使用了CountDownLatch后，像下面这样：

``` java
public static void main(String[] args) throws InterruptedException {
		CountDownLatch doneSignal = new CountDownLatch(N);
		CountDownLatch startSignal = new CountDownLatch(1);// 开始执行信号

		for (int i = 1; i <= N; i++) {
			new Thread(new Worker(i,doneSignal, startSignal)).start();// 线程启动了
		}
		
		System.out.println("begin------------");
		startSignal.countDown();// 开始执行啦
		doneSignal.await();// 等待所有的线程执行完毕
		System.out.println("Ok");

	}
```

输出结果是一个顺序的，阻塞的状态：

```log


begin------------
sdfsdfsdf1
sdfsdfsdf2
sdfsdfsdf3
sdfsdfsdf4
sdfsdfsdf5
sdfsdfsdf6
sdfsdfsdf7
sdfsdfsdf8
sdfsdfsdf9
sdfsdfsdf10
Ok

```


然后不使用CountDownLatch的话，则是下面这样，看着好像是顺序的，阻塞的，然而。。。。

```java

public static void main(String[] args) throws InterruptedException {
	System.out.println("start");
	for (int i = 1; i <= N; i++) {
		new Thread(new Worker(i)).start();// 线程启动了
	}
	System.out.println("end");
}
	
```

我们希望输出这样的结果：

 ```log
 
 start
sdfsdfsdf1
sdfsdfsdf2
sdfsdfsdf3
sdfsdfsdf4
sdfsdfsdf5
sdfsdfsdf6
sdfsdfsdf7
sdfsdfsdf8
sdfsdfsdf9
sdfsdfsdf10
end

 ```
然而真正的输出结果是这样的：

```log

start
end
sdfsdfsdf1
sdfsdfsdf2
sdfsdfsdf3
sdfsdfsdf4
sdfsdfsdf5
sdfsdfsdf6
sdfsdfsdf7
sdfsdfsdf8
sdfsdfsdf9
sdfsdfsdf10

```
