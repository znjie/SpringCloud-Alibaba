package com.yingzhitong.alibaba.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author:chuansen.zhan
 * @Date: 2020/7/15 10:53
 */
@Service
public class RestClientHelper implements ElasticSearchService{

    /*@Value("${elasticsearch.host}")
    private String host;
    @Value("${elasticsearch.port}")
    private Integer port;
    @Value("${elasticsearch.index_name}")
    private String index_name;
    @Value("${elasticsearch.type}")
    private String type;*/

    public static final String index_name = "springcloud";

    public static final String type = "springcloud";

    public static final Integer port = 9200;

    public static final String host = "localhost";

    private static RestHighLevelClient client;

    private RestClientHelper() {
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(host, port, "http")));
    }

    public static RestHighLevelClient getClient() {
        if (client == null) {
            synchronized (RestClientHelper.class) {
                if (client == null) {
                    RestClientHelper restClientHelper = new RestClientHelper();
                }
            }
        }
        return client;
    }

    /**
     * 添加数据
     * @param object
     * @throws IOException
     */
    @Override
    public void putData(Object object){
        IndexRequest indexRequest = new IndexRequest(index_name, type);
        ObjectMapper mapper = new ObjectMapper();
        byte[] json = new byte[0];
        try {
            json = mapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        indexRequest.source(json, XContentType.JSON);
        try {
            client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据
     */
    @Override
    public <T> T getData(String id, Class<T> clazz){
        GetRequest getRequest = new GetRequest(index_name, type, id);
        GetResponse getResponse = null;
        try {
            getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String json=getResponse.getSourceAsString();
        ObjectMapper mapper = new ObjectMapper();
        T t = null;
        try {
            t = mapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }


}
