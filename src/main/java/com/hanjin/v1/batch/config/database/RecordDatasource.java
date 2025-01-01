package com.hanjin.v1.batch.config.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.hanjin.v1.batch.repository.db1",
        entityManagerFactoryRef = "recordEntityManagerFactory",
        transactionManagerRef = "recordTransactionManager"
)

public class RecordDatasource{

    @Primary
    @Bean(name = "appDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.import")
    public DataSource appDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Primary
    @Bean(name = "recordEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean recordEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                             @Qualifier("appDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return builder.dataSource(dataSource).properties(properties)
                .packages("com.hanjin.v1.batch.entity.db1").persistenceUnit("Record").build();
    }


    @Primary
    @Bean(name = "recordTransactionManager")
    public PlatformTransactionManager recordTransactionManager(
            @Qualifier("recordEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}