package com.yyy.rutu.sxfy;

import com.yyy.rutu.sxfy.elastic.MyRestClientEsFactory;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;

public class RestHighLevelClientDemo {
    /**
     * https://www.elastic.co/products/elasticsearch
     *
     * @throws IOException
     * @author 官网示例:通过RestHighLevelClient连接本地elasticsearch
     */
    @Test
    public void testConnect() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
                //new HttpHost("192.168.31.145", 9200, "http")));//本地IP访问，需要设置ES文件访问方式
                new HttpHost("localhost", 9200, "http")));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.aggregation(AggregationBuilders.terms("top_10_states").field("state").size(10));

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("social-*");
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest);

        System.out.println("Result："+ searchResponse.toString());
    }
    /*
    遇到问题：
    NoClassDefFoundError: org/elasticsearch/common/xcontent/DeprecationHandler
    原因：pom.xml文件依赖elasticsearch-rest-high-level-client引入了elasticsearch-5.6.10(版本不一致：client引入版本是6.6.2)
    解决：依赖elasticsearch-rest-high-level-client中去除elasticsearch，直接引入elasticsearch-6.6.2的依赖
     */

    @Test
    public void testPostRequest() throws IOException{
        String index = "my_es";
        String type = "test_es";
        String id = "20190512003";
        String jsonSource = "{\n" +
                "    \"id\":\"20190512002\",\n" +
                "    \"name\":\"sxf-es\",\n" +
                "    \"age\":8,\n" +
                "    \"date\":\"20190512\"\n" +
                "}";
        IndexRequest request = new IndexRequest(index, type, id);
        request.source(jsonSource, XContentType.JSON);
        IndexResponse response = MyRestClientEsFactory.getRestHighLevelClient().index(request);
        System.out.println(response);

        MyRestClientEsFactory.close();//创建结束，关闭连接
    }
}
