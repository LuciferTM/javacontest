package com.lucifer.javacontest.ch2.suspend;
/**
 * suspend() resume()
 * 已经被deprecate，原因就是在挂起的时候并不释放资源，而且如果先调用了resume后调用suspend，那线程就永远挂起状态了
 *
 * 下面一个程序用 jps jstack查看下状态
 */
public class BadSuspend {
	public static Object u = new Object();
	static ChangeObjectThread t1 = new ChangeObjectThread("t1");
	static ChangeObjectThread t2 = new ChangeObjectThread("t2");

	public static class ChangeObjectThread extends Thread {
		public ChangeObjectThread(String name){
			super.setName(name);
		}
		@Override
		public void run() {
			synchronized (u) {
				System.out.println("in "+getName());
				Thread.currentThread().suspend();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		t1.start();
		Thread.sleep(100);
		t2.start();
		t1.resume();
		t2.resume();
		t1.join();
		t2.join();
	}
}
