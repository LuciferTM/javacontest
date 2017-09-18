package com.lucifer.javacontest.ch3.synctrl;

import java.util.concurrent.locks.ReentrantLock;

public class FairLock implements Runnable {

	//默认是不公平的锁，由ReentrantLock的构造参数指定
	public static ReentrantLock fairLock = new ReentrantLock();
//	public static ReentrantLock fairLock = new ReentrantLock(true);

	@Override
	public void run() {
		while(true){
		try{
			fairLock.lock();
			Thread.sleep(100);
			System.out.println(Thread.currentThread().getName()+" 获得锁");
		} catch (InterruptedException e){

		}
		finally{
			fairLock.unlock();
		}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		FairLock r1 = new FairLock();
		Thread t1=new Thread(r1,"Thread_t1");
		Thread t2=new Thread(r1,"Thread_t2");
		t1.start();t2.start();
	}
}
