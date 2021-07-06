package com.yingzhitong.cloud.service;

import org.springframework.stereotype.Component;

/**
 * @Author:chuansen.zhan
 * @Date: 2020/4/22 10:06
 */

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return "------PaymentFallbackService  fall back paymentInfo_ok ";
    }

    @Override
    public String paymentInfo_timeOut(Integer id) {
        return "------PaymentFallbackService  fall back paymentInfo_timeOut ";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "------PaymentFallbackService  fall back  paymentInfo_TimeOut--服务宕机了";
    }
}
