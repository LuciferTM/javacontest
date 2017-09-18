package com.lucifer.javacontest.ch3.synctrl;

import java.util.concurrent.locks.ReentrantLock;

public class ReenterLock implements Runnable{
	public static ReentrantLock lock=new ReentrantLock();
	public static int i=0;
	@Override
	public void run() {
		for(int j=0;j<10000000;j++){
			//因为可以多次进行加锁，但是是持有锁的线程多次加锁。所以叫ReentrantLock
			lock.lock();
			lock.lock();
			try{
				i++;
			}finally{
				lock.unlock();
				lock.unlock();
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		ReenterLock tl=new ReenterLock();
		Thread t1=new Thread(tl);
		Thread t2=new Thread(tl);
		t1.start();t2.start();
		t1.join();t2.join();
		System.out.println(i);
	}
}
