package com.bank.lambda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 16:25 2020/7/4
 * @ Description：${JMM 的可见性的代码演示}
 * @ Modified By：
 * @Version: $1.00$
 */


class MyData{
    volatile int number=0;// 修改点一：添加volatile  修饰符，通知main 线程
    public void addTo60(){
        this.number=60;
    }
    /**
     *请注意此时number 前边加了volatile 关键字修饰**/
    public synchronized void addPlus(){
        number++;
    }
}

@SpringBootTest
@RunWith(SpringRunner.class)
public class VolatileDame {
    public static void main(String[] args) {

    }
    /**
     * 20个线程操作addPlus 理论上一个100*20=20000**/
    @Test
    public void Automice(){
        MyData myData = new MyData();
        for (int i = 0; i <20 ; i++) {
            for (int j = 1; j <=1000; j++) {
                myData.addPlus();
            }
            new Thread(()->{},String.valueOf(i)).start();

        }
        while(Thread.activeCount()>2){
            Thread.yield();//如果线程数大于2不会结束向下运行
        }
        //等待20个线程完整后，用main取得结果
       /* try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println(Thread.currentThread().getName()+"\t 结束数据显示"+myData.number);
    }
    /**
     * 1.验证volatile的可见性
     *  1.1 假设 int number=0；number 变量之前根本没有添加volatile 关键字
     *  2.验证volatile 不保证原子性
     *      2.1原子性是什么？
     *      不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以加塞或者被分隔，需要完整性，
     *      要么同时成功，要么同时失败
     *      2.2 不可保证原子性 如果结果不是=200000
     *        解决办法加原子性atomic+final 或者synchronized（过重） 也可以实现原子性
     * **/
    @Test
    public void SeeOkVolitile(){
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);//线程暂停一会
                myData.addTo60();
                System.out.println(Thread.currentThread().getName()+"\t update number value:"+myData.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();
        /**main线程一直在这里等待循环，直到number 值不是0，证明线程间不互通**/
        while(myData.number==0){
            /**变量不加volitile 修饰循环一直持续，不会走到下一步，说明线程之间不互通**/
        }
        System.out.println(Thread.currentThread().getName()+"\t mian 线程 is over"+myData.number);

    }


}
