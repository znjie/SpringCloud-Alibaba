package com.yingzhitong.alibaba.controller;

import com.yingzhitong.alibaba.service.ElasticSearchService;
import com.yingzhitong.cloud.common.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:chuansen.zhan
 * @Date: 2020/7/15 10:22
 */

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private ElasticSearchService elasticSearchService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestBody Payment payment) {
        elasticSearchService.putData(payment);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity get(String id){
        Payment payment= elasticSearchService.getData(id,Payment.class);
        return ResponseEntity.ok(payment);
    }
}
