package com.bank.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 16:32 2020/6/25
 * @ Description：${description}
 * @ Modified By：
 * @Version: $1.00$
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonResult <T> {
    private Integer code;
    private  String message;
    private T data;
    public CommonResult(Integer code, String message){
        this(code,message,null);
    }

}
