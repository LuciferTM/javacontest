package com.lucifer.javacontest.ch3.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 查看Executors 的工厂方法
 * 查看下ThreadPoolExecutor的构造函数，
 *
 *
 * 例子：
 * public ThreadPoolExecutor(int corePoolSize,
	 int maximumPoolSize,
	 long keepAliveTime,
	 TimeUnit unit,
	 BlockingQueue<Runnable> workQueue) {
	 this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
	 Executors.defaultThreadFactory(), defaultHandler);
	 }
 */


public class ThreadPoolDemo {
	public static class MyTask implements Runnable {
		@Override
		public void run() {
			System.out.println(System.currentTimeMillis() + ":Thread ID:"
					+ Thread.currentThread().getId());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		MyTask task = new MyTask();
		ExecutorService es = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			es.submit(task);
		}
	}
}
