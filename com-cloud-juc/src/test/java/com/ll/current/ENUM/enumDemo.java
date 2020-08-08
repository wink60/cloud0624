package com.ll.current.ENUM;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 20:40 2020/8/8
 * @ Description：${枚举使用详解}
 * @ Modified By：
 * @Version: $1.00$
 */
 enum CountryEnum{
     ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");
      private Integer retCode;
      private String  retMessage;

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    CountryEnum(Integer retCode, String retMessage){
         this.retCode=retCode;
         this.retMessage=retMessage;
     }
     public static CountryEnum forEach_CountryEnum(int index) {
         CountryEnum[] values = CountryEnum.values();
         for (CountryEnum element :
                 values) {
             if (index == element.getRetCode()) {
                 return element;
             }
         }
         return  null;
     }
}

public class enumDemo {
}
