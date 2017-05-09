package com.aloievets.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by Andrew on 02.05.2017.
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPersistenceUnitName("JpaSample");

        return entityManagerFactoryBean;
    }

    @Bean
    JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
}
