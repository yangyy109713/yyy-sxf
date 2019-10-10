package com.yyy.rutu.sxfy.elastic;

import com.yyy.rutu.sxfy.entity.LogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.repository.support.AbstractElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.MappingElasticsearchEntityInformation;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.data.util.TypeInformation;
import org.springframework.stereotype.Repository;

@Repository("operationLogElasticsearch")
public class OperationLogElasticsearchRepository extends AbstractElasticsearchRepository<LogEntity, String> {

    /**
     * 当AbstractElasticsearchRepository提供的方法不足以完成全部功能时
     * 即可通过该对象自定义操作行为
     */
    private ElasticsearchOperations elasticsearchOperations;

    /**
     * 注入ElasticsearchOperations，并实例化OperationLogElasticsearchRepository
     * @param elasticsearchOperations
     */
    @Autowired
    public OperationLogElasticsearchRepository(ElasticsearchOperations elasticsearchOperations){
        super(createElasticsearchEntityInformation(), elasticsearchOperations);
        this.elasticsearchOperations = elasticsearchOperations;
    }

    /**
     * 创建 ElasticsearchEntityInformation 对象
     * 该对象实现对索引对象相关信息的读取
     */
    private static ElasticsearchEntityInformation<LogEntity, String> createElasticsearchEntityInformation(){
        TypeInformation<LogEntity> typeInformation = ClassTypeInformation.from(LogEntity.class);
        ElasticsearchPersistentEntity<LogEntity> entity =
                new SimpleElasticsearchPersistentEntity<>(typeInformation);

        return new MappingElasticsearchEntityInformation<>(entity);
    }

    /**
     * id的展示方式
     * @param aLong id
     * @return
     */
    @Override
    protected String stringIdRepresentation(String aLong) {
        return aLong;
    }
}
