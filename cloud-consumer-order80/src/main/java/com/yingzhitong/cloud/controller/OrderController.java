package com.yingzhitong.cloud.controller;

import com.yingzhitong.cloud.common.CommonResult;
import com.yingzhitong.cloud.common.Payment;
import com.yingzhitong.cloud.lb.LoadBalancer;
import com.yingzhitong.cloud.lb.LoadBalancerZCS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @Author:chuansen.zhan
 * @Date: 2020/4/10 17:40
 */

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

    @Value("${paymentUrl}")
    private String paymentUrl;

    @Resource
    private RestTemplate restTemplate;

    //注入手写的轮询算法
    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private LoadBalancerZCS loadBalancerZCS;

    //服务发现
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(paymentUrl + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(paymentUrl + "/payment/get/" + id, CommonResult.class);
    }


    @GetMapping("/postForEntity/create")
    public CommonResult<Payment> creat2e(Payment payment) {
        return restTemplate.postForEntity(paymentUrl + "/payment/create", payment, CommonResult.class).getBody();
    }

    @GetMapping("/getForEntity/get/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(paymentUrl + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>(444, "操作失败");
        }
    }

    /**
     * 实现自己创建的负载负载均衡
     * @return
     */
    @GetMapping("/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (serviceInstanceList == null || serviceInstanceList.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(serviceInstanceList);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }

    @GetMapping("/zcs/payment/lb")
    public String getPaymentZCS(){
        List<ServiceInstance> serviceInstanceList=discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (serviceInstanceList==null||serviceInstanceList.size()<=0){
            return null;
        }
        ServiceInstance serviceInstance=loadBalancerZCS.instance(serviceInstanceList);
        URI uri=serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }
}
