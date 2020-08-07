package com.ll.current.LOCK;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 9:30 2020/8/7
 * @ Description：${
 *     公平锁和非公平锁
 *     new FairSync（）
 *     new unFairSync（）
 *     可重入锁：ReentranLock/Synchronized
 *     概念：指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，
 *          在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
 *          也即是说，线程可以进入任何一个它已经拥有的锁所同步着的代码块
 *
 *     作用：避免死锁
 *
 * 11	 sendSMS!
 * 11	 sendEmail!
 * 12	 sendSMS!
 * 12	 sendEmail!
 * }
 * @ Modified By：
 * @Version: $1.00$
 */
class Phone{
    public synchronized void sendSMS() throws Exception{ // 外层锁
        System.out.println(Thread.currentThread().getId()+"\t sendSMS!");
        sendEmail();//内层锁=====默认自动获取同一把锁
    }
    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getId()+"\t sendEmail!");
    }

    //--------------------------------------------------

}
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();
    }
}
