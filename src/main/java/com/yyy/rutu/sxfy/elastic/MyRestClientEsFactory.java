package com.yyy.rutu.sxfy.elastic;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class MyRestClientEsFactory {
    private static final String HOST = "localhost";
    private static final int PORT = 9200;
    private static final String SCHEMA = "http";
    private static final int CONNECT_TIME_OUT = 1000;
    private static final int SOCKET_TIME_OUT = 3000;
    private static final int CONNECTION_REQUEST_TIME_OUT = 500;

    private static final int MAX_CONNECT_NUM = 100;
    private static final int MAX_CONNECT_PRE_ROUTE = 100;

    private static HttpHost HTTP_POST = new HttpHost(HOST, PORT, SCHEMA);

    private static boolean uniqueCTO = false;//CONNECT_TIME_OUT
    private static boolean uniqueCNO = true;//CONNECT_NUM

    private static RestClientBuilder restClientBuilder;
    private static RestClient restClient;
    private static RestHighLevelClient restHighLevelClient;

    static {
        init();
    }

    public static RestClient getClient(){
        return restClient;
    }

    //获取RestHighLevelClient实例
    public static RestHighLevelClient getRestHighLevelClient(){
        if(restHighLevelClient == null){
            init();
        }
        return restHighLevelClient;
    }

    //关闭es连接
    public static void close(){
        if(restHighLevelClient != null){
            try {
                restHighLevelClient.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    //初始化：配置client连接数、延时
    public static void init(){
        restClientBuilder = RestClient.builder(HTTP_POST);
        if(uniqueCTO){
            setConnectTimeOutConfig();
        }
        if(uniqueCNO){
            setMultiConnectConfig();
        }
        restClient = restClientBuilder.build();
        restHighLevelClient = new RestHighLevelClient(restClientBuilder);
    }

    //异步http client连接延时配置
    private static void setConnectTimeOutConfig(){
        restClientBuilder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder builder) {
                builder.setConnectTimeout(CONNECT_TIME_OUT);
                builder.setSocketTimeout(SOCKET_TIME_OUT);
                builder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIME_OUT);
                return builder;
            }
        });
    }

    //异步http client连接数配置
    private static void setMultiConnectConfig(){
        restClientBuilder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder){
                httpAsyncClientBuilder.setMaxConnTotal(MAX_CONNECT_NUM);
                httpAsyncClientBuilder.setMaxConnPerRoute(MAX_CONNECT_PRE_ROUTE);
                return httpAsyncClientBuilder;
            }
        });
    }
}
