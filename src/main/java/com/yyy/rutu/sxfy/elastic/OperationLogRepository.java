package com.yyy.rutu.sxfy.elastic;

import com.yyy.rutu.sxfy.entity.LogEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface OperationLogRepository extends ElasticsearchRepository<LogEntity, Long> {

}
