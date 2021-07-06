package com.yingzhitong.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:chuansen.zhan
 * @Date: 2020/4/15 17:26
 */
@Component
public class MyLBZCS implements LoadBalancerZCS {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //获取请求访问数
    public final int getRequestCount() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("第几次访问数" + next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstanceList) {
        int index = getRequestCount() % serviceInstanceList.size();
        return serviceInstanceList.get(index);
    }
}
