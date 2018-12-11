package com.lucifer.javacontest.producer_consummer.wait_notify;

import java.util.Queue;

/**
 * Created by lucifer on 7/12/2018.
 */
public class Producer extends Thread {

    Queue<Integer> queue;
    Integer maxSize;

    public Producer(Queue<Integer> queue, Integer maxSize, String name) {
        this.queue = queue;
        this.maxSize = maxSize;
        this.setName(name);
    }


    @Override
    public void run() {
        int i = 0;
        while (i < 1000) {
            synchronized (queue) {
                if (queue.size() >= maxSize) {
                    queue.notifyAll();
                }
                System.out.println("Produce:" + i);
                queue.offer(i++);
            }
        }
    }
}
