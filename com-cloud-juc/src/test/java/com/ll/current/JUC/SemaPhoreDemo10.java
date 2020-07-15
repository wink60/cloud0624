package com.ll.current.JUC;

import java.util.concurrent.*;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 10:40 2020/7/6
 * @ Description：${//信号灯
 *       争夺车位
 *       7个人抢三个车位，抢到的停留三秒剩余的继续抢车位
 *
 *      用于： 多线程的并发控制和资源的互斥
 *
 *      扩展：如果只有一个资源相当于synchronized  持有资源20秒 改版
 * }
 * @ Modified By：
 * @Version: $1.00$
 */
public class SemaPhoreDemo10 {
    public static void main(String[] args)throws ExecutionException ,InterruptedException {
        Semaphore semaphore = new Semaphore(3);//模拟资源类有3个车位

        for (int i = 0; i <7 ; i++) {
            final int temp=i;
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t-》 抢到"+"车位"+temp);
                    try {
                        TimeUnit.SECONDS.sleep(2);//持有资源2秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"\t *《-离开了车位"+temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }

    }
}
