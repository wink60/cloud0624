package com.ll.current.JVM;

import java.util.Random;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 18:49 2020/7/12
 * @ Description：${description}
 * @ Modified By：
 * @Version: $1.00$
 */
public class MyJvm {
    public static void main(String[] args) {
        long maxMemory = Runtime.getRuntime().maxMemory();//返回java 虚拟机试图使用的最大内存量
        long totalMemory = Runtime.getRuntime().totalMemory();//返回java 虚拟机中的内存总量
        System.out.println("-Xmx:MAX_MEMORY="+maxMemory+"(字节)，"+(maxMemory/(double)1024/1024)+"MB");
        System.out.println("-Xms:Total_MEMORY="+totalMemory+"(字节)，"+(totalMemory/(double)1024/1024)+"MB");

        String str="www.baidu.com";
        while(true){
            str+=str+new Random().nextInt(88888888)+new Random().nextInt(55888888);
        }
    }
}
