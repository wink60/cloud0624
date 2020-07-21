package com.ll.current.JUC.stream;

import net.bytebuddy.asm.Advice;
import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.AllArguments;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 20:03 2020/7/19
 * @ Description：${description}
 * @ Modified By：
 * @Version: $1.00$
 */

public class User {
    private Integer id;
    private String name;

    public User() {
    }

    private Integer age;

    public User(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public static void main(String[] args) {

    }
}
