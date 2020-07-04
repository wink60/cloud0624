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

@SpringBootTest
@RunWith(SpringRunner.class)
public class VolatileDame {
    public static void main(String[] args) {

    }

    /**
     * 1.验证volatile的可见性
     *  1.1 假设 int number=0；number 变量之前根本没有添加volatile 关键字
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

class MyData{
   volatile int number=0;// 修改点一：添加volatile  修饰符，通知main 线程
    public void addTo60(){
        this.number=60;
    }
}
