package com.ll.current;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
/***
 * ArrayList默认是10个空间 object 类型的
 * 扩容到15 是10的一半 arrayList.copyOf(),第二次的是22 向下取整
 * HashMAP 是一倍
 * 线程不安全（ArrayList 线程不安全的代码）
 *
 * */

/**
 * @ Author     ：liul
 * @ Date       ：Created in 11:53 2020/7/5
 * @ Description：${
 *  1.ArrayList 是线程不安全
 *     1.故障现象
 *     java.util.concurrentModificationException
 *
 *     2.导致原因
 *
 *       多线程抢夺没有加锁的资源
 *
 *     3.解决办法
 *       1.使用Vector 线程安全代替 ArrayList
 *       2.Collections.synchronizedList(new ArrayList<>());
 *       3.jdk1.8 新的建议使用CopyOnWriteArrayList
 *
 *     4.优化建议
 *
 * }
 * @ Modified By：
 * @Version: $1.00$
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class NotSafeDemo03 {
    public static void main(String[] args) {
        Map<String,String> map=new HashMap<>();
        for (int i = 1; i <=3 ; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
    @Test
    public void hashMapNOSafeOk(){
        //Map<String,String> map=new HashMap<>();
        Map<String,String> map=new ConcurrentHashMap<>();
        for (int i = 1; i <=30 ; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
    @Test
    public  void SetNoSafeDemoOK(){
        //  Set<Object> set = new HashSet<>();
        ///解决办法使用copyOnWriteArraySet,因为set 底层是hashMap 《key,value》放在key 里边，value 是一个常量object
        Set<Object> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i <=30 ; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println("======"+set);
            },String.valueOf(i)).start();
        }

    }
     @Test
    public  void ArrayListNoSafeDemoOk(){

        //List<String> list = new Vector();
        //  List<String> list= Collections.synchronizedList(new ArrayList<>());
        // ArrayList<Object> list = new ArrayList<>();
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        for (int i = 1; i <=30 ; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println("arrayList*******"+list);
            },String.valueOf(i)).start();
        }
        /* 一个main 线程安全
        list.add("a");
        list.add("a");
        list.add("a");
        list.forEach(System.out::println);
        */
    }
}
