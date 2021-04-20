package me.kalpha.trconsumerservice.trmart.config;

import com.zaxxer.hikari.HikariDataSource;
import me.kalpha.trconsumerservice.common.Constants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
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
        entityManagerFactoryRef = "trmartEntityManagerFactory",
        transactionManagerRef = "trmartTransactionManager",
        basePackages = {"me.kalpha.trconsumerservice.trmart.repository"}//repositories
)
@EnableTransactionManagement
public class TrMartDataSourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.trmart")
    public DataSourceProperties trmartDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.trmart.hikari")
    public HikariDataSource trmartDataSource() {
        return trmartDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "trmartEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean trmartEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(true);

        Properties properties = new Properties(); // Properties에 Hibernate Config 설정 추가
        properties.setProperty("hibernate.format_sql", String.valueOf(true));
        properties.setProperty("hibernate.hbm2ddl.auto", "create");

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPersistenceUnitName(Constants.TRMART_UNIT_NAME); // EntitiManager 직접 사용을 위한 Name 설정
        factory.setPackagesToScan("me.kalpha.trconsumerservice.trmart.entity");
        factory.setDataSource(trmartDataSource());
        factory.setJpaProperties(properties);

        return factory;
    }

    @Bean
    public PlatformTransactionManager trmartTransactionManager(
            final @Qualifier("trmartEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}