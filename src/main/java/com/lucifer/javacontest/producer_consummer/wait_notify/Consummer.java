package com.lucifer.javacontest.producer_consummer.wait_notify;

import java.util.Queue;

/**
 * Created by lucifer on 7/12/2018.
 */
public class Consummer extends Thread {

    Queue<Integer> queue;

    public Consummer(Queue<Integer> queue, String name) {
        this.queue = queue;
        this.setName(name);
    }


    @Override
    public void run() {
        while(true){
            synchronized (queue){
                try {
                    if(queue.size()==0){
                        queue.notifyAll();
                    } else{
                        Integer i = queue.poll();
                        System.out.println("Consummer "+this.getName()+"Consummer:"+i);
                        sleep(50);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
