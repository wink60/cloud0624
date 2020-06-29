package com.bank.lambda.entities;

import java.math.BigDecimal;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 14:31 2020/6/29
 * @ Description：${description}
 * @ Modified By：
 * @Version: $1.00$
 */
public class UserInfo {
    private Integer age;

    private String name;

    private String address;

    private BigDecimal money;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "年龄：" + age + ", 名称：" + name + ", 地址：" + address;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(!(obj instanceof UserInfo)) {
            return false;
        }

        UserInfo userInfo = (UserInfo)obj;
        return userInfo.getName().equals(name) && userInfo.getAddress().equals(address) && userInfo.getAge().equals(age);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result += 31 * address.hashCode();
        result += 31 * age.hashCode();
        result += 31 * name.hashCode();
        result += 31 * money.hashCode();
        return result;
    }
}
