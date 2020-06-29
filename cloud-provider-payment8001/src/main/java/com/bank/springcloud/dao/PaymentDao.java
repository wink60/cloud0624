package com.bank.springcloud.dao;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 8:38 2020/6/25
 * @ Description：${Payment接口}
 * @ Modified By：
 * @Version: $1.00$
 */

import com.bank.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper /**推荐使用，@Repository 可能会报错**/

public interface PaymentDao {
    public int create(Payment payment);//写擦uozuo
    public Payment getPaymentById(@Param("id") Long id);
}
