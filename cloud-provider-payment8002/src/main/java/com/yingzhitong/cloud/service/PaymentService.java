package com.yingzhitong.cloud.service;
import com.yingzhitong.cloud.common.Payment;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author:chuansen.zhan
 * @Date: 2020/4/10 14:04
 */
public interface PaymentService {

    public int create(Payment payment);
    public Payment getPaymentById(@RequestParam("id") Long id);
}
