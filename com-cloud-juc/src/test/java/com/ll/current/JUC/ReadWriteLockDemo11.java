package com.ll.current.JUC;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


class Mycache{
    private volatile Map<String,Object> map=new HashMap<>();
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();


    public void put(String key,Object value){
       readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t ->开始写入数据"+key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"\t ->写入成功"+key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

   }
    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t   开始读取成功"+key);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t   读取成功"+result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
/**
 * @ Author     ：liul
 * @ Date       ：Created in 10:40 2020/7/6
 * @ Description：${//读写锁
 *  多个线程读取一个资源没有问题，为了满足并发量，多个线程可以共同读取资源
 *  但是
 *  如果有一个线程要去更改线程，其他线程不能对该线程进行读写
 *
 * 小总结：
 *    读-读能共存
 *    读-写不能共存
 *    写-写不能共存
 * }
 * @ Modified By：
 * @Version: $1.00$
 */
public class ReadWriteLockDemo11 {
    public static void main(String[] args)throws ExecutionException ,InterruptedException {
        Mycache mycache = new Mycache();

        for (int i = 0; i <5 ; i++) {
            final int tempInt=i;
            new Thread(()->{
                mycache.put(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();
        }

        for (int i = 0; i <5 ; i++) {
            final int tempA=i;
            new Thread(()->{
                mycache.get(tempA+"");
            },String.valueOf(i)).start();
        }
    }
}
