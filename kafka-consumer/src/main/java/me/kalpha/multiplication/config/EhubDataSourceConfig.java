package me.kalpha.multiplication.config;

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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "ehubEntityManagerFactory",
        transactionManagerRef = "ehubTransactionManager",
        basePackages = {"me.kalpha.multiplication.repository"}//repositories
)
@EnableTransactionManagement
public class EhubDataSourceConfig {
    private final JpaProperties jpaProperties;
    private final HibernateProperties hibernateProperties;

    @Autowired
    public EhubDataSourceConfig(JpaProperties jpaProperties, HibernateProperties hibernateProperties) {
        this.jpaProperties = jpaProperties;
        this.hibernateProperties = hibernateProperties;
    }
    @Bean
    @ConfigurationProperties("app.datasource.ehub")
    public DataSourceProperties ehubDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.ehub.configuration")
    public DataSource ehubDataSource() {
        return ehubDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "ehubEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean ehubEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        hibernateProperties.setDdlAuto("none");
        jpaProperties.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
        var properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder
                .dataSource(ehubDataSource())
                .properties(properties)
                .packages("me.kalpha.multiplication.entity")//entities
                .build();
    }
    @Primary
    @Bean
    public PlatformTransactionManager ehubTransactionManager(
            final @Qualifier("ehubEntityManagerFactory") LocalContainerEntityManagerFactoryBean ehubEntityManagerFactory) {
        return new JpaTransactionManager(ehubEntityManagerFactory.getObject());
    }
}