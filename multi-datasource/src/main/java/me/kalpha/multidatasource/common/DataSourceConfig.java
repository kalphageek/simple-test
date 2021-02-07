package me.kalpha.multidatasource.common;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Objects;

//@Configuration
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "batchEntityManagerFactory",
//        transactionManagerRef = "batchTransactionManager",
//        basePackages = {"me.kalpha.multidatasource.batch"}
//)
//@EnableTransactionManagement(proxyTargetClass = true)
public class DataSourceConfig {
//
//    private final JpaProperties jpaProperties;
//    private final HibernateProperties hibernateProperties;
//
//    @Autowired
//    public DataSourceConfig(JpaProperties jpaProperties, HibernateProperties hibernateProperties) {
//        this.jpaProperties = jpaProperties;
//        this.hibernateProperties = hibernateProperties;
//    }
//
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.catalog")
//    public DataSourceProperties catalogDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    @Primary
//    public HikariDataSource catalogDataSource() {
//        return catalogDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.batch")
//    public DataSourceProperties batchDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    public HikariDataSource batchDataSource() {
//        return batchDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
//    }
//
//    @Bean
//    @Qualifier("batchEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean batchEntityManagerFactory(EntityManagerFactoryBuilder builder) {
////        hibernateProperties.setDdlAuto("create");
////        var properties = hibernateProperties.determineHibernateProperties(
////                jpaProperties.getProperties(), new HibernateSettings());
//        return builder.dataSource(batchDataSource())
////                .properties(properties)
//                .packages("me.kalpha.multidatasource.batch")
//                .persistenceUnit("batchPU")
//                .build();
//    }
//
//    @Bean
//    @Qualifier("batchTransactionManager")
//    public static JpaTransactionManager batchTransactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
//        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
//        jpaTransactionManager.setJpaDialect(new HibernateJpaDialect());
//
//        return jpaTransactionManager;
//    }
}