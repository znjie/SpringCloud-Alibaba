package com.yingzhitong.cloud.service.impl;
import com.yingzhitong.cloud.common.Payment;
import com.yingzhitong.cloud.mapper.PaymentMapper;
import com.yingzhitong.cloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author:chuansen.zhan
 * @Date: 2020/4/10 14:04
 */

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentMapper paymentMapper;

    @Override
    public int create(Payment payment) {
        return paymentMapper.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentMapper.getPaymentById(id);
    }
}
