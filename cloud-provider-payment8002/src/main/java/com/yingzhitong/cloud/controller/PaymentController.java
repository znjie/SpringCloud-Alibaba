package com.yingzhitong.cloud.controller;

import com.yingzhitong.cloud.common.CommonResult;
import com.yingzhitong.cloud.common.Payment;
import com.yingzhitong.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author:chuansen.zhan
 * @Date: 2020/4/10 14:07
 */

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;


    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("结果" + result);
        if (result > 0) {
            return new CommonResult(200, "建立成功,serverPort:" + serverPort, result);
        } else {
            return new CommonResult(444, "失败", null);
        }

    }

    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, payment);
        } else {
            return new CommonResult(444, "失败", null);
        }
    }

    @GetMapping("/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}
