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
输出结果是一个顺序的，与主线程同步的状态：

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


### 4、How CountDownLatch works in Java


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
    这句话导致了我们之前启动的10个线程全都处于等待状态，他们正在等待发号施令。那么这些阻塞的线程，什么时候开始运行呢？
    根据前面我们说的，当count＝0的时候就开始继续往下执行了。所以你也许知道下一步怎么触发他们了。
    万事俱备，只欠东风！
 
 5、给东风呗，让10个线程run起来了，没错，通过一句话就可以了：
 
   ```java
   startSignal.countDown();// 开始执行啦
   ```
   
   
   我们通过`countDown()`方法把count变为了0，这时候10个线程就跑起来了。
   自然每个线程执行完都必须要--1啊，就是要调用完成信号的`countDown()`方法。
   
   
 6、就这样，当10个线程运行完以后，由于每个线程执行完都会对count减1。自然10个线程执行完以后，`doneSignal` 的count就为0。
    我们知道，当count为0时，之前一直await的main线程，也就是主线程就继续往下执行了。
    
    
    main线程的代码：
    
    ```java
    
    	System.out.println("begin------------");
		startSignal.countDown();// 开始执行啦
		doneSignal.await();// 等待所有的线程执行完毕
		System.out.println("Ok");
		
    ```


### 5、sdfsdf


  Open Declaration   java.util.concurrent.locks.AbstractQueuedSynchronizer.Node


Wait queue node class. 

The wait queue is a variant of a "CLH" (Craig, Landin, and Hagersten) lock queue. CLH locks are normally used for spinlocks. We instead use them for blocking synchronizers, but use the same basic tactic of holding some of the control information about a thread in the predecessor of its node. A "status" field in each node keeps track of whether a thread should block. A node is signalled when its predecessor releases. Each node of the queue otherwise serves as a specific-notification-style monitor holding a single waiting thread. The status field does NOT control whether threads are granted locks etc though. A thread may try to acquire if it is first in the queue. But being first does not guarantee success; it only gives the right to contend. So the currently released contender thread may need to rewait. 

To enqueue into a CLH lock, you atomically splice it in as new tail. To dequeue, you just set the head field. 

```log

      +------+  prev +-----+       +-----+
 head |      | <---- |     | <---- |     |  tail
      +------+       +-----+       +-----+

```
 

Insertion into a CLH queue requires only a single atomic operation on "tail", so there is a simple atomic point of demarcation from unqueued to queued. Similarly, dequeing involves only updating the "head". However, it takes a bit more work for nodes to determine who their successors are, in part to deal with possible cancellation due to timeouts and interrupts. 

The "prev" links (not used in original CLH locks), are mainly needed to handle cancellation. If a node is cancelled, its successor is (normally) relinked to a non-cancelled predecessor. For explanation of similar mechanics in the case of spin locks, see the papers by Scott and Scherer at http://www.cs.rochester.edu/u/scott/synchronization/ 

We also use "next" links to implement blocking mechanics. The thread id for each node is kept in its own node, so a predecessor signals the next node to wake up by traversing next link to determine which thread it is. Determination of successor must avoid races with newly queued nodes to set the "next" fields of their predecessors. This is solved when necessary by checking backwards from the atomically updated "tail" when a node's successor appears to be null. (Or, said differently, the next-links are an optimization so that we don't usually need a backward scan.) 

Cancellation introduces some conservatism to the basic algorithms. Since we must poll for cancellation of other nodes, we can miss noticing whether a cancelled node is ahead or behind us. This is dealt with by always unparking successors upon cancellation, allowing them to stabilize on a new predecessor, unless we can identify an uncancelled predecessor who will carry this responsibility. 

CLH queues need a dummy header node to get started. But we don't create them on construction, because it would be wasted effort if there is never contention. Instead, the node is constructed and head and tail pointers are set upon first contention. 

Threads waiting on Conditions use the same nodes, but use an additional link. Conditions only need to link nodes in simple (non-concurrent) linked queues because they are only accessed when exclusively held. Upon await, a node is inserted into a condition queue. Upon signal, the node is transferred to the main queue. A special value of status field is used to mark which queue a node is on. 

Thanks go to Dave Dice, Mark Moir, Victor Luchangco, Bill Scherer and Michael Scott, along with members of JSR-166 expert group, for helpful ideas, discussions, and critiques on the design of this class.
