package com.ll.current.JUC;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 10:40 2020/7/6
 * @ Description：${
 *      等够7人开饭做加法CyclicBarrier
 *      做减法CountDownLatch
 * }
 * @ Modified By：
 * @Version: $1.00$
 */
public class CyclicBarrierDemo09 {
    public static void main(String[] args)throws ExecutionException ,InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("等够7人开饭");
        });
        for (int i = 0; i <7 ; i++) {
            final int tempInt=i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 第"+tempInt+"个人已经到了");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } finally {
                }
            },String.valueOf(i)).start();
        }
    }
}
