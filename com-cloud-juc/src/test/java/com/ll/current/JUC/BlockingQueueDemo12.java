package com.ll.current.JUC;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * @ Author     ：liul
 * @ Date       ：Created in 10:40 2020/7/6
 * @ Description：${//阻塞队列
 *   在多线程领域：所谓阻塞，在某些请款下会挂起线程（即阻塞），一旦条件满足，被挂起的线程又会自动唤醒
 *
 *
 *   场景：火锅店/车站候车厅
 *
 *
 *
 * }
 * @ Modified By：
 * @Version: $1.00$
 */
public class BlockingQueueDemo12{
    public static void main(String[] args)throws ExecutionException ,InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        //前三秒运行，第四秒超时停止运行 false
        System.out.println(blockingQueue.offer("a"));//true
        System.out.println(blockingQueue.offer("n"));//true返回特殊值 boolean
        System.out.println(blockingQueue.offer("c"));//true
        System.out.println(blockingQueue.offer("a",3L, TimeUnit.SECONDS));//false


      /*  blockingQueue.put("a");//此处会阻塞
        blockingQueue.put("b");
        blockingQueue.put("c");
      //  blockingQueue.put("d");//此处会阻塞
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take()); 无数据*/

       /* System.out.println(blockingQueue.offer("a"));//true
        System.out.println(blockingQueue.offer("n"));//true返回特殊值 boolean
        System.out.println(blockingQueue.offer("c"));//true
        System.out.println(blockingQueue.offer("d"));//true
        System.out.println(blockingQueue.poll());//a
        System.out.println(blockingQueue.poll());//n
        System.out.println(blockingQueue.poll());//c
        System.out.println(blockingQueue.poll());//null*/

       /* blockingQueue.add("a");
        String element = blockingQueue.element();//如果队列为空报错
        System.out.println(element);*/
       /* boolean a = blockingQueue.add("a");
        boolean b = blockingQueue.add("b");
        boolean c = blockingQueue.add("c");
       // boolean d = blockingQueue.add("d");//添加超出队列3的存储空间报错
        String remove = blockingQueue.remove();
        String remove1 = blockingQueue.remove();
        String remove2 = blockingQueue.remove();
        String remove3 = blockingQueue.remove();//删除超出队列3的空间报错
        System.out.println("======"+remove);*/



    }
}
