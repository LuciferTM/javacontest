package com.lucifer.javacontest.ch2.interrupt;

/**
 * public void Thread.interrupt() //实例方法，中断线程
 * public boolean Thread.isInterrupt() //实例方法，判定是否被中断
 * public static boolean Thread.interrupted() //静态方法，判定是否被中断，并清除当前中断状态
 */
public class InterruputThread {
	public static void main(String[] args) throws InterruptedException {
		Thread t1=new Thread(){
			@Override
			public void run(){
				while(true){
					if(Thread.currentThread().isInterrupted()){
						System.out.println("Interruted!");
						break;
					}
					Thread.yield();
				}
			}
		};
		t1.start();
		Thread.sleep(2000);
		t1.interrupt();
	}
}
