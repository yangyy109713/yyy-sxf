package com.yyy.rutu.sxfy.elastic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.security.user.User;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EsIndexRestHighLevelClientApi {

    private static RestHighLevelClient client = MyRestClientEsFactory.getRestHighLevelClient();

    /**
     * 创建索引，并同步获取操作结果
     * @param index
     * @param type
     * @param id
     * @param jsonSource
     * @return IndexResponse
     */
    public static IndexResponse postRequest(String index, String type, String id, String jsonSource){
        //构建请求
        IndexRequest request = new IndexRequest(index, type, id);
        //将要保存的索引以JSON格式关联到请求中
        request.source(jsonSource, XContentType.JSON);
        //Java客户端发起保存索引请求
        IndexResponse response = null;
        try {
            //如果已存在，则不新增
            if(!exists(index, type, id)) response = client.index(request);
        }catch (IOException e){
            e.printStackTrace();
        }
        //等待结果
        //System.out.println(response);
        return response;
    }

    /**
     * 根据单关键字查询索引
     * @param index 索引名称
     * @param type  索引类型
     * @param name  索引字段
     * @param start 开始位置
     * @param size  查询总数
     */
    public static SearchResponse query(String index, String type, String name,
                                       int start, int size){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("name", name));
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolQueryBuilder);
        sourceBuilder.from(start);
        sourceBuilder.size(size);
        sourceBuilder.fetchSource(new String[] {"id", "name"}, new String[]{});

        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types(type);
        searchRequest.source(sourceBuilder);
        SearchResponse response = null;
        try {
            response = client.search(searchRequest, RequestOptions.DEFAULT);
            /*System.out.println("query:" + JSONObject.toJSONString(response));
            SearchHits hits = response.getHits();
            SearchHit[] queryHits = hits.getHits();
            for (SearchHit hit : queryHits){
                System.out.println("query -> " + hit.getSourceAsString());
            }*/
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            MyRestClientEsFactory.close();
        }
        return response;
    }

    /**
     * 多关键字关联查询索引
     * @param keyword1
     * @param keyword2
     * @param startDate
     * @param endDate
     * @param start
     * @param size
     * @return SearchResponse
     */
    public static SearchResponse pageQueryRequest(String keyword1, String keyword2,
                                                  String startDate, String endDate,
                                                  int start, int size){
        // searchSourceBuilder查询语句的最外层部分，
        // 包括查询分页的起始，查询语句的核心，查询结果的排序，查询结果截取部分返回等一系列配置
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询开始
        searchSourceBuilder.from(start);
        //查询结果
        searchSourceBuilder.size(size);
        //查询等待时间
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        MatchQueryBuilder matchQueryBuilder;
        matchQueryBuilder = QueryBuilders.matchQuery("message", keyword1 + "" + keyword2);
        //同时满足2个查询条件
        matchQueryBuilder.operator(Operator.AND);
        //查询在时间区间范围内的结果
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("date");
        if(!"".equals(startDate)){
            rangeQueryBuilder.gte(startDate);
        }
        if(!"".equals(endDate)){
            rangeQueryBuilder.lte(endDate);
        }
        //将两个查询合并
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(matchQueryBuilder);
        boolQueryBuilder.must(rangeQueryBuilder);
        //排序
        FieldSortBuilder fsb = SortBuilders.fieldSort("date");
        fsb.order(SortOrder.DESC);
        searchSourceBuilder.sort(fsb);

        searchSourceBuilder.query(boolQueryBuilder);
        System.out.println(searchSourceBuilder);

        SearchRequest searchRequest = new SearchRequest("request");
        searchRequest.types("doc");
        searchRequest.source(searchSourceBuilder);

        SearchResponse response = null;
        try {
            response = client.search(searchRequest);
            SearchHits searchHits = response.getHits();
            int totalRecordNum = (int) searchHits.getTotalHits();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("date", "2019-05-12");
            for (SearchHit hit : searchHits){
                Map<String, Object> map = hit.getSourceAsMap();
                User user = JSON.parseObject(jsonObject.toString(), User.class);
                System.out.println(user);
            }
            client.close();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            MyRestClientEsFactory.close();//创建结束，关闭连接
        }
        return response;
    }

    /**
     * 删除索引
     * @param index
     * @param type
     * @param id
     * @return
     */
    public static int deleteById(String index, String type, String id){
        DeleteRequest deleteRequest = new DeleteRequest(index, type, id);
        DeleteResponse response = null;
        boolean flag = true;
        try {
            response = client.delete(deleteRequest);
            flag = exists(index, type, id);
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("delete: " + JSON.toJSONString(response));
        return flag == false ? 0 : 1;//0删除成功；1删除失败
    }

    /**
     * 判断索引是否已存在
     * @param index
     * @param type
     * @param id
     * @return
     */
    public static boolean exists(String index, String type, String id){
        GetRequest getRequest = new GetRequest(index, type, id);
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exists = false;
        try {
            exists = client.exists(getRequest, RequestOptions.DEFAULT);
            System.out.println("exists-索引是否存在: " + exists);
        }catch (IOException e){
            e.printStackTrace();
        }
        return exists;
    }

    /**
     * 更新索引信息
     * @param index
     * @param type
     * @param id
     * @param jsonSource
     * @return
     */
    public static UpdateResponse update(String index, String type, String id, String jsonSource){
        UpdateRequest updateRequest = new UpdateRequest(index, type, id);
        //updateRequest.doc(JSON.toJSONString(jsonSource), XContentType.JSON);//json重复格式化会报错
        updateRequest.doc(jsonSource, XContentType.JSON);
        UpdateResponse updateResponse = null;
        try {
            updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
            //System.out.println("update: " + JSON.toJSONString(updateResponse));
        }catch (IOException e){
            e.printStackTrace();
        }
        return updateResponse;
    }

    /**
     * 关闭连接
     */
    public static void closeConn(){
        MyRestClientEsFactory.close();
    }
}
