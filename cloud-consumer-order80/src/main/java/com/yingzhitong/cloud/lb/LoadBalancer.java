package com.yingzhitong.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Author:chuansen.zhan
 * @Date: 2020/4/15 16:15
 */
public interface LoadBalancer {

    //获取该微服务名称下的实例
    ServiceInstance instances(List<ServiceInstance> serviceInstanceList);

}
