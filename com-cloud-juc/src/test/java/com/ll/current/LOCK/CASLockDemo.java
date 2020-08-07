package com.ll.current.LOCK;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 14:37 2020/8/7
 * @ Description：${自旋锁
 *  概念：是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁，这样的好处是减少线程上下文的消耗，
 *  缺点： 循环会消耗CPU
 *  好处：循环比较获取直到成功为止，没有类似wait的阻塞
 *
 *  编程：     public  final int getAndInt(Object var1,long var2,int var4){
 *              int var5;
 *              do{
 *                  var5=this.getIntVolatile(var1,var2);
 *              }while(!this.compareAndSwapInt(var1,var2,var5,var5+var4));
 *              return var5;
 *            }
 *
 *  题目：通过CAS 操作完成自旋锁，A线程先进来调用myLock 方法自己持有锁5秒钟，
 *       B随后进来发现，当前有线程持有锁，不是null，所以只能自旋等待，
 *       直到A释放锁后B随后抢到
 *
 * }
 * @ Modified By：
 * @Version: $1.00$
 */
public class CASLockDemo {
    // 原子引用线程
    AtomicReference<Thread> atomicRefence = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in!");
        while(!atomicRefence.compareAndSet(null,thread)){
            System.out.println(Thread.currentThread().getName()+"\t is in cycle");
        }
    }
    public void myUnlock(){
        Thread thread = Thread.currentThread();
        atomicRefence.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t my UnLock!");
    }

    public static void main(String[] args) {
        CASLockDemo casLockDemo = new CASLockDemo();

        new Thread(()->{
            casLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            casLockDemo.myUnlock();
        },"AA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            casLockDemo.myLock();
            casLockDemo.myUnlock();
        },"BB").start();
    }
}
