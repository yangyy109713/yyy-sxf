package com.yyy.rutu.sxfy.dataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author yyy
 * @date 2019.09.03
 */
@Configuration
@MapperScan(basePackages = {"com.yyy.rutu.sxfy.dao"}, sqlSessionFactoryRef = "sxfySqlSessionFactory")
public class SfxyMyBatisConfig {

    @Autowired
    private DataSource sxfyDataSource;

    @Autowired
    private ApplicationContext context;

    @Bean
    public SqlSessionFactory sxfySqlSessionFactory(ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(sxfyDataSource);
        factoryBean.setMapperLocations(applicationContext.getResources("classpath*:mapper/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sxfySqlSessionTemplate() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sxfySqlSessionFactory(context));
        return template;
    }
}
