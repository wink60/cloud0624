package com.ll.current.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 16:42 2020/8/2
 * @ Description：${description}
 * @ Modified By：
 * @Version: $1.00$
 */
public class ABADemo {
    static AtomicReference<Integer> atomicReference =new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference=new AtomicStampedReference<>(100,1);
    public static void main(String[] args) {
        System.out.println("===============ABA问题的产生=============");
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"t1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(atomicReference.compareAndSet(100,2019)+"\t"+atomicReference.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();

        System.out.println("===================解决ABA问题===============");

        new Thread(()->{
            try {
                int stamp = atomicStampedReference.getStamp();
                TimeUnit.SECONDS.sleep(1);
                atomicStampedReference.compareAndSet(100,2009,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
                atomicStampedReference.compareAndSet(2009,2998,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
                System.out.println(Thread.currentThread().getName()+"\t"+atomicStampedReference.getStamp());
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t3").start();

        new Thread(()->{
            try {
                int stamp = atomicStampedReference.getStamp();
                TimeUnit.SECONDS.sleep(3);
                atomicStampedReference.compareAndSet(100,102,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
                atomicStampedReference.compareAndSet(102,103,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
                System.out.println(Thread.currentThread().getName()+"\t"+atomicStampedReference.getStamp());
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t4").start();

    }
}
