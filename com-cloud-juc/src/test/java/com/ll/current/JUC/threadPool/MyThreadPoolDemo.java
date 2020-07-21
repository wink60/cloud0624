package com.ll.current.JUC.threadPool;

import java.util.concurrent.*;


/**
 * @ Author     ：liul
 * @ Date       ：Created in 10:40 2020/7/6
 * @ Description：${/ 线程池
 *
 *
 *
 *
 *
 * }
 * @ Modified By：
 * @Version: $1.00$
 */
public class MyThreadPoolDemo {
    public static void main(String[] args)throws ExecutionException ,InterruptedException {
        System.out.println("电脑内核数："+Runtime.getRuntime().availableProcessors());
        //配置最大线程数的标准 ：电脑的核数+1
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());


      /*  ExecutorService threadPool= Executors.newCachedThreadPool();//一池n个工作线程，类似一个银行有n个受理窗口（根据需要自动扩容）
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();//一池一个工作线程，类似银行有一个受理窗口
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);//一池五个受理线程  类似银行五个受理窗口
       //底层实现：ThreadPoolExecutor; 以上三种线程均不用只用ThreadPoolExecutor；
*/
        try {
            //模拟10个顾客办理业务
            for (int i = 0; i <19 ; i++) {
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
