package com.yingzhitong.cloud.controller;

import com.yingzhitong.cloud.common.CommonResult;
import com.yingzhitong.cloud.service.PaymentFeignService;
import com.yingzhitong.cloud.common.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author:chuansen.zhan
 * @Date: 2020/4/15 20:01
 */

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;


    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    /**
     * 超时控制
     * @return
     */
    @GetMapping("/payment/feign/timeOut")
    public String paymentFeignTimeOut(){
        return paymentFeignService.paymentFeignTimeOut();
    }

}
