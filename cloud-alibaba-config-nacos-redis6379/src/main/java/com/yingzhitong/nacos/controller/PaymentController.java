package com.yingzhitong.nacos.controller;

import com.yingzhitong.nacos.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:chuansen.zhan
 * @Date: 2020/7/30 10:11
 */

@RestController
@RequestMapping("/Payment")
@RefreshScope
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * 缓存穿透一
     * @param id
     * @return
     */
    @RequestMapping("/queryById")
    public ResponseEntity queryById(String id) {
        return ResponseEntity.ok(paymentService.queryById(id));
    }
    /**
     * 缓存穿透二
     * @param id
     * @return
     */
    @RequestMapping("/getPaymentById")
    public ResponseEntity getPaymentById(Integer id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }







}
