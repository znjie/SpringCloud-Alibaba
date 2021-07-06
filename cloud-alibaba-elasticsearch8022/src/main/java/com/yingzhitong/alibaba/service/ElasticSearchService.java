package com.yingzhitong.alibaba.service;

/**
 * @Author:chuansen.zhan
 * @Date: 2020/7/15 11:20
 */
public interface ElasticSearchService {

    void putData(Object object);

    <T> T getData(String id, Class<T> clazz);
}
