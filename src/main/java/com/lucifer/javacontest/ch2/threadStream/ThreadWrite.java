package com.lucifer.javacontest.ch2.threadStream;

import java.io.PipedOutputStream;

/**
 * Created by lucifer on 7/12/2018.
 */
public class ThreadWrite extends Thread {

    private WriteData write;
    private PipedOutputStream out;

    public ThreadWrite(WriteData write,PipedOutputStream out) {
        this.write=write;
        this.out=out;
    }

    @Override
    public void run(){
        write.writeMethod(out);
    }

}

