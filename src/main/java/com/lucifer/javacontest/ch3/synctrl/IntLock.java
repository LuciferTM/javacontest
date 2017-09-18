package com.lucifer.javacontest.ch3.synctrl;

import java.util.concurrent.locks.ReentrantLock;

public class IntLock implements Runnable {
	public static ReentrantLock reentrantLock_1 = new ReentrantLock();
	public static ReentrantLock reentrantLock_2 = new ReentrantLock();
	int lock;
	/**
	 * 控制加锁顺序，方便构造死锁
	 * @param lock
	 */
	public IntLock(int lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			if (lock == 1) {
				/**
				 * lockInterruptibly()是可以对中断进行响应的锁申请方法
				 * 即在等待锁的过程中，可以响应中断
				 */
				reentrantLock_1.lockInterruptibly();
				try{
					Thread.sleep(500);
				}catch(InterruptedException e){}
				reentrantLock_2.lockInterruptibly();
			} else {
				reentrantLock_2.lockInterruptibly();
				try{
					Thread.sleep(500);
				}catch(InterruptedException e){}
				reentrantLock_1.lockInterruptibly();
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (reentrantLock_1.isHeldByCurrentThread())
				reentrantLock_1.unlock();
			if (reentrantLock_2.isHeldByCurrentThread())
				reentrantLock_2.unlock();
			System.out.println(Thread.currentThread().getId()+":线程退出");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		IntLock r1 = new IntLock(1);
		IntLock r2 = new IntLock(2);
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		Thread.sleep(1000);
		//中断其中一个线程
		t2.interrupt();
	}
}
