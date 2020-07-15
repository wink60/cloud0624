package com.ll.current.JUC;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

class MyThread1 implements Runnable{

    @Override
    public void run() {
        System.out.println("AAAAA");
    }
}
class MyThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("Come in Callable!");
        return 1024;
    }
}

/**
 * @ Author     ：liul
 * @ Date       ：Created in 10:40 2020/7/6
 * @ Description：${
 *
 * }
 * @ Modified By：
 * @Version: $1.00$
 */
public class CallableDemo07 {
    public static void main(String[] args)throws ExecutionException ,InterruptedException{
        /*Thread thread1 = new Thread(new MyThread1(),"AAA" );
        thread1.start();*/
        FutureTask<Integer> futureTask = new FutureTask(new MyThread2());

        new Thread(futureTask,"BB").start();
        Integer integer = futureTask.get();
        System.out.println(integer);

    }
}
