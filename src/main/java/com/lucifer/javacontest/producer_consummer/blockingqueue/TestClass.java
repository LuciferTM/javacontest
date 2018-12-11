package com.lucifer.javacontest.producer_consummer.blockingqueue;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by lucifer on 9/12/2018.
 */
public class TestClass {
    public static void main(String args[]){
        /**
         * 根据各种场景可以选择不同的阻塞队列来进行
         * 有界队列ArrayBlockingQueue
         * 无界队列LinkedBlockingQueue
         * 同步队列SynchronoueQueue
         * 优先级队列PriorityBlockingQueue
         * */
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        Producer p1 = new Producer(queue);
        Consummer c1 = new Consummer(queue);
        Consummer c2 = new Consummer(queue);
        Consummer c3 = new Consummer(queue);

        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(p1);
        service.submit(c1);
        service.submit(c2);
        service.submit(c3);
    }
}
