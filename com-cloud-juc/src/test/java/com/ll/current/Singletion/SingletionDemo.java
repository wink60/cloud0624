package com.ll.current.Singletion;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 12:09 2020/8/3
 * @ Description：${description}
 * @ Modified By：
 * @Version: $1.00$
 */
public class SingletionDemo {
    private static SingletionDemo instance=null;
    private SingletionDemo(){
        System.out.println(Thread.currentThread().getName()+"构造方法");
    }
    public static SingletionDemo getInstance(){
        if(instance==null){
            instance=new SingletionDemo();
        }
      return instance;
    }

    public static void main(String[] args) {
     /* System.out.println(SingletionDemo.getInstance()==SingletionDemo.getInstance());
        System.out.println(SingletionDemo.getInstance()==SingletionDemo.getInstance());
        System.out.println(SingletionDemo.getInstance()==SingletionDemo.getInstance());
        System.out.println("++++++++++++++++++++++++++++++++");
       */ for (int i = 0; i <20 ; i++) {
            new Thread(()->{
                SingletionDemo.getInstance();
               // System.out.println(Thread.currentThread().getName()+" ============="+SingletionDemo.getInstance());
         },String.valueOf(i)).start();
        }

    }
}
