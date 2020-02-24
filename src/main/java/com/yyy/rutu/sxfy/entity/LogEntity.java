package com.yyy.rutu.sxfy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * ElasticSearch索引对象必须标注@Document注解，indexName为索引名，type为索引类型
 * 加上了@Document注解之后，默认情况下这个实体中所有的属性都会被建立索引、并且分词
 * （PS：这是ElasticSearch特性，同样的索引，可以分为不同的类型，来分别做索引）
 */
@Document(indexName = "logging", type = "operation")
public class LogEntity {

    /**
     * 坑一：这里的id主键必须为String类型，且必须加@Id注解，否则创建索引时，无法将主键转换成
     *     索引id，这样索引id就是null
     * @Id是org.springframework.data.annotation.Id
     */
    @Id
    private Integer id;

    private String name;

    private String operation;

    /**
     * @JsonFormat 服务端到前端时间格式转换，即出参格式化
     * 可以设置时间格式、时区
     * pattern：需要转换的时间日期的格式
     * timezone：时区，设置为东八区，避免时间转换存在误差
     * 作用：实体类接收数据库查询结果时，直接按 pattern 转换时间格式，然后传给前端
     * 注意：使用 @JsonFormat 注解，可以在属性的Get方法上，也可以在属性上
     *
     * @DateTimeFormat 前端到服务端时间格式转换，即入参格式化
     * 可以设置时间格式、时区
     * pattern：需要转换的时间日期的格式
     * timezone：时区，设置为东八区，避免时间转换存在误差
     * 作用：实体类接收前端数据时，直接按 pattern 转换时间格式，然后存入数据库
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date time;

    public LogEntity() {
    }

    public LogEntity(String name, String operation, Date time) {
        this.name = name;
        this.operation = operation;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
