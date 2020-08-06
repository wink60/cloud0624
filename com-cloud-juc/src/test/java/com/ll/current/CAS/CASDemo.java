package com.ll.current.CAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 14:18 2020/8/1
 * @ Description：${CAS 是什么？？
 *  CAS 是计算机底层的一个原语，用在多线程操作CPU的时候为了保证线程的安全比较并交换
 *  为了保证多线程的可见性资源参数被valite 修饰
 *  cas 操作不允许被打断
 * }
 * @ Modified By：
 * @Version: $1.00$
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        atomicInteger.compareAndSet(5,2024);
        atomicInteger.compareAndSet(5,1024);

        atomicInteger.getAndIncrement();//a++
        System.out.println("++++++++"+atomicInteger);
        System.out.println( atomicInteger.getAndIncrement());
        System.out.println( atomicInteger.getAndIncrement());
        System.out.println( atomicInteger.getAndIncrement());
        System.out.println( atomicInteger.getAndIncrement());
    }
}
