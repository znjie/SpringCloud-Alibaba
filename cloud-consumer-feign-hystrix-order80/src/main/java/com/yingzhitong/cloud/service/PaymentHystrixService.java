package com.yingzhitong.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author:chuansen.zhan
 * @Date: 2020/4/18 16:09
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/paymentInfo_ok/{id}")
     String paymentInfo_ok(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/paymentInfo_timeOut/{id}")
    String paymentInfo_timeOut(@PathVariable("id") Integer id);

    /**
     * 服务降级
     * @param id
     * @return
     */
    @GetMapping("/payment/hystrix/paymentInfo_TimeOut/{id}")
    String paymentInfo_TimeOut(@PathVariable("id") Integer id);



}
