package com.yingzhitong.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Author:chuansen.zhan
 * @Date: 2020/4/15 17:21
 */
public interface LoadBalancerZCS {

    ServiceInstance instance(List<ServiceInstance> serviceInstanceList);
}
