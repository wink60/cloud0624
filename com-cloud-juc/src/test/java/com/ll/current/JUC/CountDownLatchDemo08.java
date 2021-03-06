package com.ll.current.JUC;

import java.util.concurrent.*;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 10:40 2020/7/6
 * @ Description：${
 *        题目原型：班长关门走人原型
 * }
 * @ Modified By：
 * @Version: $1.00$
 */
public class CountDownLatchDemo08 {
    public static void main(String[] args)throws ExecutionException ,InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i=0;i<6;i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 离开教室");
                countDownLatch.countDown();//计数减少一个
            },String.valueOf(i)).start();
        }
        countDownLatch.await();//等待countdownLatch 计数器执行完毕
        System.out.println(Thread.currentThread().getName()+"班长关门走人");


    }
        public static void closeDoor(){
        //默认这种有顺序问题
            for (int i=0;i<6;i++) {
                new Thread(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 离开教室");
                },String.valueOf(i)).start();
            }


            System.out.println(Thread.currentThread().getName()+"班长关门走人");

        }


}
