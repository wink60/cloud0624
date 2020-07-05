package com.ll.current;
@FunctionalInterface //函数编程只能有一个接口加了此注解
interface Foo{
    // public void sayHello();
    public int add(int a, int b);

    default public int mul(int x, int y){
        return x*y;
    }
    public static int div(int x, int y){
        return x/y;
    }


}
/**
 * @ Author     ：liul
 * @ Date       ：Created in 9:29 2020/7/5
 * @ Description：${
 *   1.Lamba 表达式
 *     拷贝中括号 写死右箭头 落地大括号
 *   2.@FunctionalInterface 函数式编程 可直接使用方法
 *      public 普通方法一个
 *      default 多个
 *      static  1个
 * @ Modified By：
 * @Version: $1.00$
 */
public class LambdaExpressDemo02 {
    public static void main(String[] args) {
      /*  Foo foo = new Foo(){
            public void sayHello() {
                System.out.println("****hello!");
            }

            @Override
            public int add(int a, int b) {
                return 0;
            }
        };
        foo.sayHello();*/
       //替代上边 简化

      /* Foo foo=()->{
          System.out.println("*********hello!");
        };
       foo.sayHello();*/


       Foo foo=(int x,int y)->{
           System.out.println("come params******");
           return x+y;
       };
        System.out.println(foo.add(1,2));
        System.out.println(foo.mul(3,4));
        System.out.println(Foo.div(12,2));

    }
}
