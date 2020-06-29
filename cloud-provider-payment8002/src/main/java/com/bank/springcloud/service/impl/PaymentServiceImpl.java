package com.bank.springcloud.service.impl;

import com.bank.springcloud.dao.PaymentDao;
import com.bank.springcloud.entities.Payment;
import com.bank.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 8:59 2020/6/25
 * @ Description：${实现类}
 * @ Modified By：
 * @Version: $1.00$
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource /**java 自带的Autowire 也行 spring带的**/
    private PaymentDao paymentDao;


    @Override
    public int create(Payment payment) {
      return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
