package com.ll.current.JUC;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{//资源类=实例变量+实例方法
    private int number=30;//
    // List list=new ArrayList();
    Lock lock=new ReentrantLock();// 可重入锁

    public  void sale(){//以前用synchronized(重锁) 暂时不用，用新的LOCK 接口的锁
        lock.lock();
        try {
            //
            if (number>0) {
                System.out.println(Thread.currentThread().getName()+"\t 卖出第："+(number--)+"\t 张 还剩下："+number);
            }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          lock.unlock();
        }


    }

}
/**
 * @ Author     ：liul
 * @ Date       ：Created in 9:29 2020/7/5
 * @ Description：${题目 三个售票员 卖出  30张票
 *  *  笔记：如何编写企业级的多线程代码
 *  *       固定的编程套路+模板
 *  *
 *  *  1.在高内聚低耦合的前提下，线程  操作   资源类
 *  *    （对对象资源封装暴露接口）
 *      1.1 一言不合，先创建一个资源类
 *  }
 * @ Modified By：
 * @Version: $1.00$
 */
public class SaleTicketDemo01 {
    public static void main(String[] args) {// 主线程 一切程序的入口
        Ticket ticket = new Ticket();
        //Lambda 表达式
        new Thread(()->{for (int i = 0; i < 40; i++) ticket.sale();},"A").start();
        new Thread(()->{for (int i = 0; i < 40; i++) ticket.sale();},"B").start();
        new Thread(()->{for (int i = 0; i < 40; i++) ticket.sale();},"C").start();


        /*new Thread(()->{   //匿名内部类
            for (int i = 0; i < 40; i++) ticket.sale();
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) ticket.sale();
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 40; i++) ticket.sale();
        },"C").start();*/




       /* Thread t1 = new Thread();
        Thread t2 = new Thread();
        Thread t3 = new Thread();*/
       /* new Thread(new Runnable() {//匿名内部类
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "A").start();//分配一个新的 Thread对象。
        new Thread(new Runnable() {//匿名内部类
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "B").start();//分配一个新的 Thread对象。

        new Thread(new Runnable() {//匿名内部类
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "C").start();//分配一个新的 Thread对象。
*/

    }
}
