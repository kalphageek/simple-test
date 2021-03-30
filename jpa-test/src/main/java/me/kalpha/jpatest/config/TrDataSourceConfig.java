package me.kalpha.jpatest.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "trEntityManagerFactory",
        transactionManagerRef = "trTransactionManager",
        basePackages = {"me.kalpha.jpatest.tr.repository"}//repositories
)
@EnableTransactionManagement
public class TrDataSourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.tr")
    public DataSourceProperties trDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.tr.hikari")
    public HikariDataSource trDataSource() {
        return trDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "trEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean trEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(true);

        Properties properties = new Properties(); // Properties에 Hibernate Config 설정 추가
        properties.setProperty("hibernate.format_sql", String.valueOf(true));
        properties.setProperty("hibernate.hbm2ddl.auto", "create");

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPersistenceUnitName(Constants.TR_UNIT_NAME); // EntitiManager 직접 사용을 위한 Name 설정
        factory.setPackagesToScan("me.kalpha.jpatest.tr.entity");
        factory.setDataSource(trDataSource());
        factory.setJpaProperties(properties);

        return factory;
    }

    @Bean
    public PlatformTransactionManager trTransactionManager(
            final @Qualifier("trEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}