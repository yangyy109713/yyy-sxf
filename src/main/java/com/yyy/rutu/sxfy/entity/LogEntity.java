package com.yyy.rutu.sxfy.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * ElasticSearch索引对象必须标注@Document注解，indexName为索引名，type为索引类型
 * （PS：这是ElasticSearch特性，同样的索引，可以分为不同的类型，来分别做索引）
 */
@Document(indexName = "sxfy", type = "log")
public class LogEntity {

    /**
     * 坑一：这里的id主键必须为String类型，且必须加@Id注解，否则创建索引时，无法将主键转换成
     *     索引id，这样索引id就是null
     * @Id是org.springframework.data.annotation.Id
     */
    @Id
    private String id;

    private String name;

    private String operation;

    private Date time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", operation='" + operation + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}