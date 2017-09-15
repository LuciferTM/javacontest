package com.lucifer.javacontest.ch2.badlock;

/**
 * public synchronized void increase()是实例方法，而两个线程指向不同的实例
 * 所以两个对象持有的锁不是同一把锁，这里是不同步的
 * 锁必须和实例绑定，所以改成静态方法就是同步的了
 *
 * public static synchronized void increase()
 *
 */

public class BadAccountingSync2 implements Runnable{
	static int i=0;
	public synchronized void increase(){
		i++;
	}
	@Override
	public void run() {
		for(int j=0;j<10000;j++){
			increase();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Thread t1=new Thread(new BadAccountingSync2());
		Thread t2=new Thread(new BadAccountingSync2());
		t1.start();t2.start();
		t1.join();t2.join();
		System.out.println(i);
	}
}
