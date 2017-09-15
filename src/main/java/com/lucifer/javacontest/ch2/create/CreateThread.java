package com.lucifer.javacontest.ch2.create;


/**
 * Thread.start()和Thread.run()的区别
 * start是开启一个新的线程并执行run
 * run只在当前线程之中串行执行run()的代码
 *
 */

public class CreateThread {
	public static void main(String[] args) {
		Thread t1=new Thread();
		t1.start();
	}
}
