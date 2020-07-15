package com.ll.current.JUC;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.*;

class MyThread1 implements Runnable{

    @Override
    public void run() {
        System.out.println("AAAAA");
    }
}
class MyThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("Come in Callable!***********");
        TimeUnit.SECONDS.sleep(4);
        return 1024;
    }
}

/**
 * @ Author     ：liul
 * @ Date       ：Created in 10:40 2020/7/6
 * @ Description：${
 *       多线程，第三种方法Callable
 *       不同与Runnable 的三点：call/run 有返回值 有异常
 *
 *       1.get 方法一般放在最后一行。
 *       2.Callable 调用两次计算，相同的有缓存
 * }
 * @ Modified By：
 * @Version: $1.00$
 */
public class CallableDemo07 {
    public static void main(String[] args)throws ExecutionException ,InterruptedException{
        /*Thread thread1 = new Thread(new MyThread1(),"AAA" );
        thread1.start();*/
        //资源类
        FutureTask<Integer> futureTask = new FutureTask(new MyThread2());

        new Thread(futureTask,"BB").start();
        new Thread(futureTask,"CC").start();

        System.out.println(futureTask.get());//放在最后一行
        System.out.println(Thread.currentThread().getName()+"结束信息");

    }
}
