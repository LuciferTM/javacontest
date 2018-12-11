package com.lucifer.javacontest.ch2.threadStream;

import java.io.PipedInputStream;

/**
 * Created by lucifer on 7/12/2018.
 */
public class ThreadRead extends Thread{
    private ReadData read;
    private PipedInputStream in;
    public ThreadRead(ReadData read,PipedInputStream in) {
        this.read=read;
        this.in=in;
    }

    @Override
    public void run() {
        read.ReadDate(in);
    }

}
