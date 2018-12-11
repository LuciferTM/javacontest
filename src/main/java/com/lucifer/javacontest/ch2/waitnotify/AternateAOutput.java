package com.lucifer.javacontest.ch2.waitnotify;

/**
 * Created by lucifer on 7/12/2018.
 */

/**
 * 两个线程交替输出1-100
 *
 * */
public class AternateAOutput {
    private static Object lock = new Object();
    static Integer num = 1;
    public static class T1 extends Thread{
        @Override
        public void run(){
            synchronized (lock){
                try{
                    while(num<=100) {
                        if(num%2==1) {
                            System.out.println("T1:"+num);
                            num = num + 1;
                        }
                        else{
                            lock.notify();
                            lock.wait();
                        }

                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class T2 extends Thread{
        @Override
        public void run(){
            synchronized (lock){
                try{
                    while(num<=100) {
                        if(num%2==0){
                            System.out.println("T2:"+num);
                            num = num + 1;
                        }else {
                            lock.notify();
                            lock.wait();
                        }
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        Thread t1 = new T1() ;
        Thread t2 = new T2() ;
//        Thread t1_1 = new T1() ;
//        t1_1.start();
        t1.start();
        t2.start();
    }

}
