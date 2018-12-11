package com.lucifer.javacontest.producer_consummer.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by lucifer on 9/12/2018.
 */
public class Consummer implements Runnable {

    private BlockingQueue<Integer> queue;
    private volatile boolean isRunning=true;

    public Consummer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        while(isRunning){
            try {
                Integer i = queue.take();
                System.out.println("Consummer"+
                        Thread.currentThread().getId()+
                        ":"+i);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        isRunning = false;
    }
}
