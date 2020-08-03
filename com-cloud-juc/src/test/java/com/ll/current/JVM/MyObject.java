package com.ll.current.JVM;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 20:25 2020/7/6
 * @ Description：${description}
 * @ Modified By：
 * @Version: $1.00$
 */
public class MyObject {
    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(object.getClass().getClassLoader().getParent());
        MyObject myObject = new MyObject();
        System.out.println(myObject.getClass().getClassLoader());
    }
}
