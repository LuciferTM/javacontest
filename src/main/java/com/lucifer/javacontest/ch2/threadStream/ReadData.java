package com.lucifer.javacontest.ch2.threadStream;

import java.io.IOException;
import java.io.PipedInputStream;

/**
 * Created by lucifer on 7/12/2018.
 */
public class ReadData {

    public void ReadDate(PipedInputStream input) {
        try {
            System.out.println("read:");
            byte[] byteArray=new byte[20];
            int readLength=input.read(byteArray);
            while(readLength!=-1) {
                String newDate=new String(byteArray,0,readLength);
                System.out.println("read:"+newDate);
                readLength=input.read(byteArray);
            }
            System.out.println();
            input.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
