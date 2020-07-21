package com.ll.current.JUC.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 20:27 2020/7/19
 * @ Description：${函数式编程和流式计算}
 * @ Modified By：//jdk8->java.utill-stream
 * @Version: $1.00$
 */
public class StreamDemo01 {
    public static void main(String[] args) {
        /**流式计算 题目
         * 请按照 给出的数据，找出同时满足以下条件的用户，也即一下条件全部满足
         * 偶数ID，且年龄大于24且用户名转换为大写且用户名字倒排序
         * 只给出一个用户名字
         * ***/
        User user1 = new User(11, "a", 23);
        User user2 = new User(12, "b", 24);
        User user3 = new User(14, "c", 26);
        User user4 = new User(15, "d", 27);
        User user5 = new User(16, "e", 28);

        List<User> list= Arrays.asList(user1,user2,user3,user4,user5);
        list.stream()
                .filter(u->{return u.getId()%2==0;})
                .filter(u->{return u.getAge()>24;})
                .map(u->{ return u.getName().toUpperCase();}).sorted((o1, o2) -> {
                    return o2.compareTo(o1);
        }).limit(1).collect(Collectors.toList()).forEach(System.out::println);

        // 讲解map参数信息
        List<Integer> list1=Arrays.asList(1,2,3);
        list1=list1.stream().map(x->{return x*2;}).collect(Collectors.toList());

        for (Integer i:
             list1) {
            System.out.println(i);
        }
    }

    public static void FourInterface() {//
        //四大核心函数式接口
        //--------------------------Function<s,t>--------------------------
        Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
        System.out.println(function.apply("12345"));
        Function<String, Integer> function1 = s -> {
            return s.length();
        };
        System.out.println("函数式编程结果：" + function1.apply("hello"));
        //-------------------------------布尔型接口 也叫断定型接口-------------------------
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return false;
            }
        };

        Predicate<String> predicate1 = s -> {
            return s.isEmpty();
        };
        System.out.println("断定型接口" + predicate1.test("11"));
        //---------消费型接口无返回-------------------------
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("这是一个消费型接口无返回值！");
            }
        };
        Consumer<String> consumer1 = s -> {
            System.out.println();
        };
        //System.out.println("消费型接口:"+consumer1.accept("ccccc"));//报错无返回值类型
//-----------Supper 供给型接口（无参数需要返回参数）---------------
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "Hello";
            }
        };
        Supplier<String> supplier1 = () -> {
            return "SSSS";
        };
        System.out.println("供给型" + supplier1.get());
    }
}

