package com.ll.current;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Aircondition{
    private int number=0;
    private Lock lock=new ReentrantLock();//可重入锁--非公平锁
    private Condition condition=lock.newCondition();
    public void increment(){//++
        lock.lock();
        try {
            //
            while (number!=0) {
                //this.wait();是和synchronized 一起使用的
                condition.await();
            }
            //干活
            number++;
            //通知
            System.out.println(Thread.currentThread().getName()+"\t "+number);
           // this.notifyAll();
            condition.signalAll();
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          lock.unlock();
        }
    }
    public void decrement(){//--
        lock.lock();
        try {
            while (number==0) {
                //this.wait();是和synchronized 一起使用的
                condition.await();
            }
            //干活
            number--;
            //通知
            System.out.println(Thread.currentThread().getName()+"\t "+number);
            // this.notifyAll();
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


   /* 下边是老版的

   public synchronized void add()throws Exception{
         //判断
        while (number!=0) {
          this.wait();

        }
        //干活
            number++;
        //通知
        System.out.println(Thread.currentThread().getName()+"\t "+number);
        this.notifyAll();
    }
    public synchronized void reduce() throws Exception{
        while(number==0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"\t "+number);
        this.notifyAll();
    }*/
}
/**
 * @ Author     ：liul
 * @ Date       ：Created in 19:42 2020/7/5
 * @ Description：${
 *     题目：现在两个线程，可以操作初始值为零的一个变量
 *     实现一个线程对该变量加1，一个线程对该变量减一，
 *     实现交替，来10轮，变量初始值为零
 *
 *     1.  高内聚低耦合前提下，线程操作资源类
 *     2.  判断+干活+通知
 *     3.  防止线程的虚拟唤醒，只要有wait需要用while判断不能用if
 *
 *     知识点总结=多线程的编程套路+while 判断+新版写法
 *
 * }
 * @ Modified By：
 * @Version: $1.00$
 */
public class ProdConsumerDemo04 {
    public static void main(String[] args) {
        Aircondition aircondition=new Aircondition() ;

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"a").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"b").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"c").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"d").start();

      /*  new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    aircondition.add();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        },"A").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    aircondition.reduce();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    aircondition.add();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    aircondition.reduce();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"D").start();*/
    }

}
