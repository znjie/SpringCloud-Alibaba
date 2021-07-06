package com.yingzhitong.nacos.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.yingzhitong.cloud.common.Payment;
import com.yingzhitong.nacos.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author:chuansen.zhan
 * @Date: 2020/7/30 10:16
 */

@Service
public class PaymentService extends ServiceImpl<PaymentMapper, Payment> {

    /**
     * 初始化一个能够容纳10000个人元素且容错率为0.01布隆过滤器
     */
    private static final BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 10000, 0.01);

    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 解决方案一：缓存空对象
     *
     * @param id
     * @return
     */
    public Payment queryById(String id) {
        Object object = redisTemplate.opsForValue().get(id);
        //缓存查询没有命中
        if (object != null) {
            //正常返回数据
            return (Payment) object;
        }
        //缓存查询不到则进入数据库查询
        Payment payment = paymentMapper.selectById(id);
        //数据库查询不为空则放入缓存
        if (payment != null) {
            redisTemplate.opsForValue().set(id, payment, 60, TimeUnit.MINUTES);
        } else {
            //查询为空则new对象，并且过期时间设短一些
            redisTemplate.opsForValue().set(id, new Payment(), 60, TimeUnit.SECONDS);
        }
        return payment;
    }

    /**
     * 解决方案二：布隆过滤器拦截
     */
    public Payment getPaymentById(Integer id) {
        //判断是否为合法Id
        if (!bloomFilter.mightContain(id)) {
            //非法Id,则不允许继续查库
            return null;
        } else {
            Object object = redisTemplate.opsForValue().get(id);
            //缓存为空
            if (object == null) {
                //从数据库获取
                Payment payment = paymentMapper.selectById(id);
                //缓存空对象
                redisTemplate.opsForValue().set(id, payment);
            }
            return (Payment) object;
        }
    }
}
