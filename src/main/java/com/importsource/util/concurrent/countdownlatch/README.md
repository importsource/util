## CountDownLatch

### 1、What is CountDownLatch？

A synchronization aid that allows one or more threads to wait until a set of operations being performed in other threads completes. 

它提供了一种同步机制，可以允许一个或多个线程等待直到其他的线程上的一系列操作完成。


要想学会使用CountDownLatch，只要搞清楚下面三个点，就可以了：

1、指定一个count。比如：`CountDownLatch startSignal = new CountDownLatch(1);// 开始执行信号`

2、明白`await()`方法。当count变为0的时候就继续往下执行，没到0就一直卡着。

3、明白`countDown()`方法。这个方法负责递减，每次减1。


### 2、Why use CountDownLatch?

### 3、When should we use CountDownLatch in Java :

1、Use CountDownLatch when one of Thread like main thread, require to wait for one or more thread to complete, before its start doing processing.

当我们希望main线程里，必须等待其他线程执行完以后然后继续往下执行。这个时候，我们就需要用到`CountDownLatch`。

按照上面的需求，在你不知道CountDownLatch之前，你也许会这样写你的代码：

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

```java

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
				System.out.println("sdfsdfsdf"+index);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				doneSignal.countDown();
			}
		}
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


现在我们来一步步复盘CountDownLatch执行顺序。

1、首先我们定义了`CountDownLatch doneSignal = new CountDownLatch(N);`。完成信号。

2、然后我们定义了`CountDownLatch startSignal = new CountDownLatch(1);`。开始信号。

3、然后我们使用`for`循环来启动10个线程。我们分别把完成信号和开始信号传入线程。像这样：
  
  ```java
  
  for (int i = 1; i <= N; i++) {
	new Thread(new Worker(i,doneSignal, startSignal)).start();// 线程启动了
  }
  
  ```
  
 4、线程按理说已经开始执行了。然而，我们发现在`Work`类中的`run`方法里有这样一句话：
 
 ```java
    startSignal.await(); // 等待开始执行信号的发布
 ```
    这句话导致了我们之前启动的10个线程全都处于等待状态，他们正在等待发号施令。万事俱备，只欠东风！
 
 5、现在你也许已经想到了下一步要干啥了。给东风呗，让10个线程run起来了，没错，通过一句话就可以了：
 
   ```java
   startSignal.countDown();// 开始执行啦
   ```
   
   我们通过`countDown()`已经让10个线程跑起来。
   
   
 6、
