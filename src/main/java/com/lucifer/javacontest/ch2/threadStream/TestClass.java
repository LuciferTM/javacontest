package com.lucifer.javacontest.ch2.threadStream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by lucifer on 7/12/2018.
 */

/**
 java中提供了IO流使我们很方便的对数据进行操作，pipeStream是一种特殊的流，用于不同线程间直接传送数据。一个线程将数据发送到输出管道，另一个线程从输入管道读取数据。通过管道实现通信不需要借助临时文件这类东西。
 java中提供了四个类使得线程间可以通信：
 ①字节流：PipeInputStream，PipedOutputStream
 ②字符流：PipedReader，PipedWriter
 * */
public class TestClass {

    public static void main(String[] args) {
        try {
            WriteData write=new WriteData();
            ReadData read=new ReadData();
            PipedInputStream inputStream=new PipedInputStream();
            PipedOutputStream outputStream=new PipedOutputStream();
            //输出流与输入流进行连接。
            outputStream.connect(inputStream);
            //inputStream.connect(outputStream);
            ThreadRead readThread=new ThreadRead(read,inputStream);
            readThread.start();//先启动输出线程
            Thread.sleep(2000);
            ThreadWrite writeThread=new ThreadWrite(write,outputStream);
            writeThread.start();//后启动输入线程
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
