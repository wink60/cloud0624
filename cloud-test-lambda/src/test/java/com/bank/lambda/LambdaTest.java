package com.bank.lambda;



/**
 * @ Author     ：liul
 * @ Date       ：Created in 14:33 2020/6/29
 * @ Description：${description}
 * @ Modified By：
 * @Version: $1.00$
 */

import com.bank.lambda.entities.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

/**
 * @功能:【LambdaTest 】
 * @作者:代守诚
 * @日期:2018/11/14
 * @时间:14:56
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LambdaTest {


        /**
         * 并集去重
         */
        @Test
        public void testDistinct() {
            List<UserInfo> userInfoList = getUserInfo();//1-10
            List<UserInfo> infos = getUser();//5-10

            userInfoList.addAll(infos);

            //需要重写equals和hashCode方法
            List<UserInfo> userInfos = userInfoList.stream().distinct().collect(toList());

            userInfos.forEach(System.out :: println);

            //一行解决
            /**
             * 这个list的streamAPI的聚合操作collect可以让我们只关注结果，而collect方法里的collectingAndThen又是属于
             * java.util.stream.Collector，collectingAndThen操作的解释是：先执行前面的操作，然后执行第二部操作后输出结果，
             * 这里我们执行的第一步操作就是Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Person::getName)))，
             * 第二步就是将他输出为一个新的ArrayList。
             *
             * 第一步操作里又是用到Collectors接口，这次用的是toCollection方法，就是将方法里的函数或者参数转化为一个collection集合，这里，
             * 我们是将Comparator.comparing(Person::getName)转化为一个collection，这个collection是一个TreeSet。也就是有序的。因为
             * 我们需要去掉重复的值，这个set可以做到，而我们又要保持转化出来的collection依旧有序，所以使用的是一个TreeSet。
             *
             * Comparator.comparing(Person::getName)这里呢，又用到了java.util.Comparator接口，这个接口倒是挺常用的。使用的是他的comparing方法，
             * 也就是比较参数的值是否相同，里面用到的是java8的新特性lambda表达式， :: 其实和.没太大区别，举个例子，最常用的System.out.println() 可以
             * 表达为System.out::println，可以达到一样的效果
             */
//        List<UserInfo> distinct = userInfoList.stream().collect(
//                Collectors.collectingAndThen(Collectors.toCollection(() ->new TreeSet<>(Comparator.comparing(UserInfo::getName))), ArrayList::new));
//
//        distinct.forEach(System.out::println);
        }

        /**
         * 测试reduce
         */
        @Test
        public void testReduce() {
            int count = Stream.of(1, 2, 3).reduce(0, (acc, element) -> acc + element);
            System.out.println(count);
        }

        /**
         * 测试Stream，flatMap，filter等
         */
        @Test
        public void testUserInfo() {
            List<UserInfo> userInfoList = getUserInfo();
            List<UserInfo> infos = getUser();

            //寻找集合大于年龄大于15的用户集合
            List<UserInfo> u = Stream.of(userInfoList, infos).flatMap(userInfos -> userInfos.stream()).
                    filter(userInfo -> userInfo.getAge() > 15).collect(toList());

            u.forEach(System.out::println);

            //寻找集合大于年龄大于15的姓名集合
            List<String> us = Stream.of(userInfoList, infos).flatMap(userInfos -> userInfos.stream()).
                    filter(userInfo -> userInfo.getAge() > 15).map(userInfo -> userInfo.getName()).collect(toList());
            us.forEach(System.out::println);

            //并集去重
            List<UserInfo> distinct = Stream.of(userInfoList, infos).flatMap(userInfos -> userInfos.stream()).
                    collect(Collectors.collectingAndThen(Collectors.toCollection(() ->new TreeSet<>(Comparator.comparing(UserInfo::getName))), ArrayList::new));

            distinct.forEach(System.out::println);
        }

        /**
         * 测试输出一个String
         */
        @Test
        public void testSttringBuilder() {
            List<UserInfo> infos = getUser();

            String result = infos.stream().map(UserInfo::getName).collect(Collectors.joining(", ", "[", "]"));
            System.out.println(result);
        }

        /**
         * 测试下游收集器
         */
        @Test
        public void testMapping() {
            List<UserInfo> userInfoList = getUserInfo();

            Map<String, List<String>> map = userInfoList.stream().collect(groupingBy(UserInfo::getName, mapping(UserInfo::getAddress, toList())));

            map.forEach((k, v) ->{
                v.forEach(System.out::println);
            });
        }

        /**
         * 测试并行化统计
         */
        @Test
        public void testCount() {
            List<UserInfo> userInfoList = getUserInfo();

            //整数求和
            int count = userInfoList.parallelStream().mapToInt(UserInfo::getAge).sum();
            System.out.println(count);

            //整数求最大
            OptionalInt opMax = userInfoList.stream().mapToInt(UserInfo::getAge).max();
            System.out.println(opMax.getAsInt());

            //整数求最小
            OptionalInt opMin = userInfoList.stream().mapToInt(UserInfo::getAge).min();
            System.out.println(opMin.getAsInt());

            //整数求平均值
            OptionalDouble opAverage = userInfoList.stream().mapToInt(UserInfo::getAge).average();
            System.out.println(opAverage.getAsDouble());

            //计算金额
            BigDecimal bigDecimal = userInfoList.stream().map(UserInfo::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
            System.out.println(bigDecimal.doubleValue());
        }

        /**
         * 测试将集合中一个字段转成大写
         */
        @Test
        public void testStringUpperCase() {
            List<UserInfo> userInfoList = getUserInfo();

            //测试将字段转成大写
            List<String> list = userInfoList.stream().map(userInfo -> userInfo.getName().toUpperCase()).collect(toList());
            //测试将字段转成大写
            List<String> stringList = userInfoList.stream().map(userInfo -> userInfo.getName().toUpperCase()).collect(Collectors.<String>toList());
            //测试将字段首字母转成大写
            List<String> strings = userInfoList.stream().map(userInfo -> userInfo.getName()).collect(Collectors.<String>toList());
            List<String > sList = strings.stream().map(value ->{
                char firstChar = Character.toUpperCase(value.charAt(0));
                return firstChar + value.substring(1);
            }).collect(Collectors.<String>toList());

            list.forEach(System.out::println);
            stringList.forEach(System.out::println);
            sList.forEach(System.out::println);
        }

        /**
         * 测试集合list转换
         */
        @Test
        public void testList() {
            List<UserInfo> userInfoList = getUserInfo();
            List<UserInfo> infos = getUser();

            //将list转成map
            Map<Integer, UserInfo> map = userInfoList.stream().collect(Collectors.toMap(UserInfo::getAge, (k) -> k));
            System.out.println(map);
            map.forEach((k1, k2) -> {
                System.out.println(k2);
            });
            Map<Integer, UserInfo> userInfoMap = userInfoList.stream().collect(Collectors.toMap(UserInfo::getAge, userInfo -> userInfo,(k1, k2) -> k1));
            userInfoMap.forEach((k1, k2) -> {
                System.out.println(k2);
            });

            //根据某个属性进行分组计数
            Map<String, Long> countMap = infos.stream().collect(groupingBy(UserInfo::getName, Collectors.counting()));
            System.out.println(countMap);

            //根据整个实体对象分组计数，当期为String时常用
            Map<UserInfo, Long> userMap = infos.stream().collect(groupingBy(Function.identity(), Collectors.counting()));
            System.out.println(userMap);

            //根据分组的key值对结果进行排序、放进另一个map中并输出
            Map<Integer, UserInfo> longUserInfoMap = new HashMap<>();
            userInfoMap.entrySet().stream().sorted(Map.Entry.<Integer, UserInfo>comparingByKey().reversed()).
                    forEachOrdered(x ->longUserInfoMap.put(x.getKey(), x.getValue()));
            System.out.println(longUserInfoMap);

            //分组并对其中一个属性求和
            Map<String, Integer> uMap = userInfoList.stream().collect(groupingBy(UserInfo::getName, Collectors.summingInt(UserInfo::getAge)));
            System.out.println(uMap);

            //多个集合分组并对其中一个属性求和
            Map<String, Integer> userM = Stream.of(userInfoList, infos).flatMap(userInfos -> userInfos.stream()).collect(groupingBy(UserInfo::getName, Collectors.summingInt(UserInfo::getAge)));
            System.out.println(userM);
        }

        /**
         * 测试交集、差集、并集
         */
        @Test
        public void testCollection() {
            List<UserInfo> userInfoList = getUserInfo();
            List<UserInfo> infos = getUser();

            //测试交集
            List<UserInfo> list = userInfoList.stream().filter(userInfo -> infos.contains(userInfo)).collect(toList());
            list.forEach(System.out::println);

            //测试差集
            List<UserInfo> infoList = userInfoList.stream().filter(userInfo -> !infos.contains(userInfo)).collect(toList());
            infoList.forEach(System.out::println);

            //测试并集
            List<UserInfo> userInfos = Stream.of(userInfoList, infos).flatMap(users -> users.stream()).collect(toList());
            userInfos.forEach(System.out::println);

        }


        private List<UserInfo> getUserInfo() {
            List<UserInfo> userInfoList = new ArrayList<>();

            for(int count = 1; count < 10; count ++) {
                UserInfo userInfo = new UserInfo();
                userInfo.setAge(11 + count);
                userInfo.setAddress("峡谷" + count);
                userInfo.setName("dai" + count);
                userInfo.setMoney(new BigDecimal(1.11));
                userInfoList.add(userInfo);
            }
            return userInfoList;
        }

        private List<UserInfo> getUser() {
            List<UserInfo> userInfoList = new ArrayList<>();

            for(int count = 5; count < 10; count ++) {
                UserInfo userInfo = new UserInfo();
                userInfo.setAge(11 + count);
                userInfo.setAddress("峡谷" + count);
                userInfo.setName("dai" + count);
                userInfo.setMoney(new BigDecimal(1.11));
                userInfoList.add(userInfo);
            }

            return userInfoList;
        }
    }
