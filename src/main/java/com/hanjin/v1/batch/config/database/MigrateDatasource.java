package com.hanjin.v1.batch.config.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.hanjin.v1.batch.repository.db2",
        entityManagerFactoryRef = "migrateEntityManagerFactory",
        transactionManagerRef = "migrate2TransactionManager"
)
public class MigrateDatasource {

    @Bean(name = "migrateDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.migrate")
    public DataSource migrateDataSource() {
        return DataSourceBuilder.create().build();
    }




    @Bean(name = "migrateEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean bookEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("migrateDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return builder.dataSource(dataSource).properties(properties)
                .packages("com.hanjin.v1.batch.entity.db2").persistenceUnit("MigrateRecord").build();
    }


    @Bean(name = "migrateTransactionManager")
    public PlatformTransactionManager bookTransactionManager(
            @Qualifier("migrateEntityManagerFactory") EntityManagerFactory bookEntityManagerFactory) {
        return new JpaTransactionManager(bookEntityManagerFactory);
    }

}