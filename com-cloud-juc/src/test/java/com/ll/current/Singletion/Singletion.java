package com.ll.current.Singletion;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 10:52 2020/8/3
 * @ Description：${单例模式-》枚举类}
 * @ Modified By：
 * @Version: $1.00$
 */
public enum Singletion {
    INSTANCE;

    public void doSomething(){
        System.out.println("doSomething");
    }

    public static void main(String[] args) {
        Singletion.INSTANCE.doSomething();
    }

}
