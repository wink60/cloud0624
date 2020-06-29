package com.bank.springcloud.service;

import com.bank.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 8:56 2020/6/25
 * @ Description：${description}
 * @ Modified By：
 * @Version: $1.00$
 */
public interface PaymentService {
    public int create(Payment payment);//写擦uozuo
    public Payment getPaymentById(@Param("id") Long id);

}
