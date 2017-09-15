
package com.lucifer.javacontest.ch2.stop;

/**
 * 看stopMe()
 *
 * Many uses of <code>stop</code> should be replaced by code that simply
 * modifies some variable to indicate that the target thread should
 * stop running.  The target thread should check this variable
 * regularly, and return from its run method in an orderly fashion
 * if the variable indicates that it is to stop running.  If the
 * target thread waits for long periods (on a condition variable,
 * for example), the <code>interrupt</code> method should be used to
 * interrupt the wait.
 *
 */
public class StopThreadSafe {
    public static User u = new User();

    public static class User {
        private int id;

        private String name;

        public User() {
            id = 0;
            name = "0";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User [id=" + id + ", name=" + name + "]";
        }
    }

    public static class ChangeObjectThread extends Thread {
        volatile boolean stopme = false;
        
        public void stopMe(){
            stopme = true;
        }
        @Override
        public void run() {
            while (true) {
                if (stopme){
                    System.out.println("exit by stop me");
                    break;
                }
                synchronized (u) {
                    int v = (int) (System.currentTimeMillis() / 1000);
                    u.setId(v);
                    //Oh, do sth. else
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (u) {
                    if (u.getId() != Integer.parseInt(u.getName())) {
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadObjectThread().start();
        while (true) {
            ChangeObjectThread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(150);
            t.stopMe();
        }
    }
}
