package com.ll.current.JUC.stream;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class MyTask extends RecursiveTask<Integer>{
    private static final Integer ADJUST_VALUE=10;
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if((end-begin)<=ADJUST_VALUE){
            for (int i = begin; i <= end; i++) {
                result=result+i;
            }
        }else{
            int middle=(end+begin)/2;
            MyTask task01=new MyTask(begin,middle);
            MyTask task02=new MyTask(middle+1,end);
            task01.fork();
            task02.fork();
            result=task01.join()+task02.join();
        }
        return result;
    }



}
/**
 * @ Author     ：liul
 * @ Date       ：Created in 20:28 2020/7/19
 * @ Description：${ForkJoin
 * ---分支合并框架------------------*
 *
 *  大事化小，小事合并
 *  fork 分开小的
 *  join 聚合
 * }
 * @ Modified By：
 * @Version: $1.00$
 */
public class ForkJoinDemo02 {
    public static void main(String[] args)throws Exception {
        //线程操作资源类
        MyTask myTask=new MyTask(0,100);
        ForkJoinPool threadPool=new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = threadPool.submit(myTask);
         System.out.println(forkJoinTask.get());

        threadPool.shutdown();
    }
    /*ForkJoinPool;
    ForkJoinTask;*/
}
