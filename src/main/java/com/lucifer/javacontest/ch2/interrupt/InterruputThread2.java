package com.lucifer.javacontest.ch2.interrupt;

/**
 *
 *
 */
public class InterruputThread2 {
	public static void main(String[] args) throws InterruptedException {
		Thread t1=new Thread(){
			@Override
			public void run(){
				while(true){
					Thread.yield();
				}
			}
		};
		t1.start();
		Thread.sleep(2000);
		t1.interrupt();
	}
}
