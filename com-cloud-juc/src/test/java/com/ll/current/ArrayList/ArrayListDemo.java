package com.ll.current.ArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 10:50 2020/8/6
 * @ Description：${ArrayList在单线程下线程安全，多线程下线程不安全
 * add 或者remove 操作的时候
 *
 *       java.util.ConcurrentModificationException
 * }
 * @ Modified By：
 * @Version: $1.00$
 */
public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
       /* for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }*/
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 360; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        //add remove 操作是失败的有两种解决办法
        /**
         * 1.新建一个删除数据的list 调用removeAll();
         * */
        list.removeAll(list);
        System.out.println("===333333==="+list);

        /**
         * 2.方法是，iterator也有一个remove方法如下，其中有一个重要的操作为expectedModCount = modCount;这样就保证了两者的相等。　
         * */
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            if (next.equals("1222")){
                iterator.remove();
            }
        }
        for(String b:list){
            System.out.println(b);
        }
        /***
         * 方法3：
         * 更改使用
         * List list = Collections.synchronizedList(new ArrayList(...));
         * **/


    }
}
