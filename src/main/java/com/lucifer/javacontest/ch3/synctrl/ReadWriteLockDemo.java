package com.lucifer.javacontest.ch3.synctrl;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 读写锁对比普通锁，2秒和20秒的区别，性能就是其实就是读操作对写操作的比值
 */


public class ReadWriteLockDemo {
	private static Lock lock=new ReentrantLock();
	private static ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
	private static Lock readLock = readWriteLock.readLock();
	private static Lock writeLock = readWriteLock.writeLock();
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private int value;
	
	public Object handleRead(Lock lock) throws InterruptedException{
		try{
			lock.lock();
			Thread.sleep(1000);
			System.out.println(df.format(System.currentTimeMillis()));
			return value;				
		}finally{
		lock.unlock();
		}
	}

	public void handleWrite(Lock lock,int index) throws InterruptedException{
		try{
			lock.lock();
			Thread.sleep(1000);
			System.out.println(df.format(System.currentTimeMillis()));
			value=index;
		}finally{
		lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final ReadWriteLockDemo demo=new ReadWriteLockDemo();
		Runnable readRunnale=new Runnable() {
			@Override
			public void run() {
				try {
					demo.handleRead(readLock);
//					demo.handleRead(lock);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Runnable writeRunnale=new Runnable() {
			@Override
			public void run() {
				try {
					demo.handleWrite(writeLock,new Random().nextInt());
//					demo.handleWrite(lock,new Random().nextInt());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
        for(int i=0;i<18;i++){
            new Thread(readRunnale).start();
        }
        
        for(int i=18;i<20;i++){
            new Thread(writeRunnale).start();
        }
	}
}
