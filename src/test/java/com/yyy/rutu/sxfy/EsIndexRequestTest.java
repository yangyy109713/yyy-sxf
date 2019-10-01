package com.yyy.rutu.sxfy;

import com.alibaba.fastjson.JSONObject;
import com.yyy.rutu.sxfy.elastic.EsIndexRestHighLevelClientApi;
import com.yyy.rutu.sxfy.entity.MyEsEntity;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EsIndexRequestTest {

    @Test
    public void testIndexCreate(){
        MyEsEntity esDTO = new MyEsEntity();
        setEsDTO(esDTO);
        String jsonSource = esDTO2Json(esDTO);
        System.out.println(jsonSource);
        IndexResponse response = EsIndexRestHighLevelClientApi.postRequest(esDTO.getIndex(), esDTO.getType(), esDTO.getId(), jsonSource);
        System.out.println("create response -> " + response);
    }

    @Test
    public void testIndexPageQuery(){
        SearchResponse response = EsIndexRestHighLevelClientApi.query("my_es", "test_es",
                "yyy01", 0, 100);
        System.out.println("query response:" + JSONObject.toJSONString(response));
        SearchHits hits = response.getHits();
        SearchHit[] queryHits = hits.getHits();
        for (SearchHit hit : queryHits){
            System.out.println("query result -> " + hit.getSourceAsString());
        }
    }

    @Test
    public void testIndexDelete(){
        int del = EsIndexRestHighLevelClientApi.deleteById("my_es", "test_es", "2019070301");
        if (del == 0){
            System.out.println("delete result -> " + "删除成功/索引不存在！");
        }else {
            System.out.println("delete result -> " + "删除失败！");
        }
    }

    @Test
    public void testIndexUpdate(){
        MyEsEntity esDTO = new MyEsEntity();
        setEsDTO(esDTO);
        String jsonSource = esDTO2Json(esDTO);
        UpdateResponse response = EsIndexRestHighLevelClientApi.update("my_es", "test_es", "2019070302", jsonSource);
        System.out.println("update response -> " + response);
    }


    private void setEsDTO(MyEsEntity esDTO){
        esDTO.setIndex("my_es");
        esDTO.setType("test_es");
        esDTO.setId(Date2String(new Date())+(int)Math.random()*100+2);
        esDTO.setAge((int)Math.random()*10+18);
        esDTO.setName("yyy"+(int)Math.random()*10+1);
        esDTO.setDate(new Date());
    }

    private String esDTO2Json(MyEsEntity esDTO){
        String jsonSource = "{\n" +
                "    \"id\":\"" +
                esDTO.getId() +
                "\",\n" +
                "    \"name\":\"" +
                esDTO.getName() +
                "\",\n" +
                "    \"age\":" +
                esDTO.getAge() +
                ",\n" +
                "    \"date\":\"" +
                Date2String(esDTO.getDate()) +
                "\"\n" +
                "}";
        return jsonSource;
    }

    private String Date2String(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyMMdd");
        return sdf.format(date) == null ? "" : sdf.format(date);
    }
}
