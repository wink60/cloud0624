package com.bank.lambda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 10:54 2020/7/4
 * @ Description：${description}
 * @ Modified By：
 * @Version: $1.00$
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class javaTest {
    @Test
    public void getResult(){
        int a = 0;
        int b = 0;
        System.out.println("a++:0"+(a++));
        System.out.println("a++:1"+a);
        System.out.println("++b:1"+(++b));
        System.out.println("++b:1"+b);
        int aa = 0;
        for (int i = 0; i < 99; i++) {
            aa = aa ++;
        }
        System.out.println("结果=0"+aa);

        int bb = 0;
        for (int i = 0; i < 99; i++) {
            bb = ++ bb;
        }
        System.out.println("结果=99"+bb);
    }
}
