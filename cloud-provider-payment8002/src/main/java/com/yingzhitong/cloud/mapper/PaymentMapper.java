package com.yingzhitong.cloud.mapper;
import com.yingzhitong.cloud.common.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author:chuansen.zhan
 * @Date: 2020/4/10 13:51
 */
@Mapper
public interface PaymentMapper {
    public int create(Payment payment);
    public Payment getPaymentById(@RequestParam("id") Long id);
}
