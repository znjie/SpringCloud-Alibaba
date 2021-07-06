package com.yingzhitong.nacos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yingzhitong.cloud.common.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author:chuansen.zhan
 * @Date: 2020/7/30 10:14
 */

@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {
}
