package com.lucifer.javacontest.ch3.synctrl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemapDemo implements Runnable{
    //只有5个线程可以同时获取信号量的许可
    final Semaphore semp = new Semaphore(5);
    @Override
    public void run() {
        try {
            semp.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId()+":done!");
            semp.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        //20个线程，所以每次5个5个执行
        final SemapDemo demo=new SemapDemo();
        for(int i=0;i<20;i++){
            exec.submit(demo);
        }
    }
}
