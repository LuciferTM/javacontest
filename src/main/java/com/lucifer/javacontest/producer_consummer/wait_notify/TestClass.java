package com.lucifer.javacontest.producer_consummer.wait_notify;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lucifer on 7/12/2018.
 */
public class TestClass {

    public static void main(String[] args){
        Queue<Integer> queue = new LinkedList<>();
        Producer p = new Producer(queue,100,"P1");
        Consummer c1 = new Consummer(queue,"C1");
        Consummer c2 = new Consummer(queue,"C2");
        Consummer c3 = new Consummer(queue,"C3");
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(p);
        service.submit(c1);
        service.submit(c2);
        service.submit(c3);
    }
}
