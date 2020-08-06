package com.ll.current.JUC.stream;

import java.util.concurrent.CompletableFuture;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 20:28 2020/7/19
 * @ Description：${description}
 * @ Modified By：
 * @Version: $1.00$
 */
public class CompletableFutureDemo03 {
    public static void main(String[] args) throws Exception{
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回");
        });
        completableFuture1.get();
        // 异步会调
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "有返回值");
           // int age=10/0;
            return 1024;
        });
        completableFuture2.whenComplete((t,u)->{
            System.out.println("-----t="+t);
            System.out.println("------u="+u);
        }).exceptionally(f->{
            System.out.println("-------exception："+f.getMessage());
            return 444;
        }).get();
        System.out.println(completableFuture2.get());
    }
}
