package me.kalpha.multidatasource.common;

import com.zaxxer.hikari.HikariDataSource;
import me.kalpha.multidatasource.catalog.User;
import me.kalpha.multidatasource.catalog.UserRole;
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
        entityManagerFactoryRef = "catalogEntityManagerFactory",
        transactionManagerRef = "catalogTransactionManager",
        basePackages = {"me.kalpha.multidatasource.catalog"}//repositories
)
@EnableTransactionManagement
public class CatalogDataSourceConfig {
    private final JpaProperties jpaProperties;
    private final HibernateProperties hibernateProperties;

    @Autowired
    public CatalogDataSourceConfig(JpaProperties jpaProperties, HibernateProperties hibernateProperties) {
        this.jpaProperties = jpaProperties;
        this.hibernateProperties = hibernateProperties;
    }
    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.catalog")
    public DataSourceProperties catalogDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.catalog.configuration")
    public DataSource catalogDataSource() {
        return catalogDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "catalogEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean catalogEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        hibernateProperties.setDdlAuto("none");
        jpaProperties.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
        var properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder
                .dataSource(catalogDataSource())
                .properties(properties)
                .packages("me.kalpha.multidatasource.catalog")//entities
                .build();
    }
    @Primary
    @Bean
    public PlatformTransactionManager catalogTransactionManager(
            final @Qualifier("catalogEntityManagerFactory") LocalContainerEntityManagerFactoryBean catalogEntityManagerFactory) {
        return new JpaTransactionManager(catalogEntityManagerFactory.getObject());
    }
}