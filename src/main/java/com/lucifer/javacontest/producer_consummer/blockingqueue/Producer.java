package com.lucifer.javacontest.producer_consummer.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

/**
 * Created by lucifer on 9/12/2018.
 */
public class Producer implements Runnable{
    private BlockingQueue<Integer> queue;
    private volatile boolean isRunning = true;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        Integer i = 0;
        while(i<100){
            try {
                queue.put(i++);
                System.out.println("Producer"+
                        Thread.currentThread().getId()+
                        ":"+i);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        isRunning=false;
    }
}
