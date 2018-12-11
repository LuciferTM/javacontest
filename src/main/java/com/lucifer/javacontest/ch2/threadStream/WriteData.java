package com.lucifer.javacontest.ch2.threadStream;

import java.io.IOException;
import java.io.PipedOutputStream;

/**
 * Created by lucifer on 7/12/2018.
 */
public class WriteData {
    public void writeMethod(PipedOutputStream out) {
        try {
            System.out.println("write:");
            for(int i=0;i<300;i++) {
                String outDate=""+(i+1);
                out.write(outDate.getBytes());
                System.out.print(outDate);
            }
            System.out.println();
            out.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
