package com.ll.current.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
    private int number=1;//a:1 b:2 c:3
    private Lock lock=new ReentrantLock();
    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();
    public void printA(){
        lock.lock();
        try {
            while(number!=1){
               c1.await();
            }
            for (int i = 0; i <5 ; i++) {
                System.out.println("AAA");
            }
            number=2;
            c2.signal();
            while (number!=2){
                c2.await();
            }
            for (int i = 0; i <10 ; i++) {
                System.out.println("BBB");
            }
            number=3;
            c3.signal();
            while (number!=3){
                c3.await();
            }
            for (int i = 0; i <15 ; i++) {
                System.out.println("CCC");
            }
            number=1;
            c1.signal();

        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          lock.unlock();
        }

    }
  /*  public void printB(){
        lock.lock();
        try {
            while (number!=2){
                c2.await();
            }
            for (int i = 0; i <10 ; i++) {
                System.out.println("BBB");
            }
            number++;
            c3.signalAll();
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          lock.unlock();
        }


    }
    public void printC(){
        lock.lock();
        try {
            while(number!=3){
              c3.await();
            }
            for (int i = 0; i <15 ; i++) {
                System.out.println("CCC");
            }
            number-=2;
            c1.signalAll();
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          lock.unlock();
        }


    }*/
}
/**
 * @ Author     ：liul
 * @ Date       ：Created in 9:24 2020/7/6
 * @ Description：${
 *     备注：多线程之间按顺序调用，实现A->B-》C 三个线程启动，要求如下：
 *
 *     AA 打印5次  BB 打印10次 CC 打印15次
 *     接着
 *     AA 打印5次  BB 打印10次 CC 打印15次
 *     来10轮
 * }
 * @ Modified By：
 * @Version: $1.00$
 */
public class ConditionDemo06 {
    public static void main(String[] args) {
        ShareData data = new ShareData();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                data.printA();
            }
        },"AAA").start();
      /*  new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                data.printB();
            }

        },"BBB").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printC();
            }

        },"CCC").start();*/
    }
}
