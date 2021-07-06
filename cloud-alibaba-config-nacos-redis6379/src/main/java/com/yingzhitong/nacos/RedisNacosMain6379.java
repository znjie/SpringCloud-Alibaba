package com.yingzhitong.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author:chuansen.zhan
 * @Date: 2020/7/14 15:15
 */

@SpringBootApplication
@EnableDiscoveryClient
public class RedisNacosMain6379 {
    public static void main(String[] args) {
        SpringApplication.run(RedisNacosMain6379.class, args);
    }
}

