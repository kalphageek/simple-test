package me.kalpha.hikarimultidb.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
public class DataSourceConfiguration {

    @Bean(Constant.CATALOG_DATASOURCE)
    @Primary
    @ConfigurationProperties(prefix = "datasource.catalog.hikari")
    public DataSource catalogDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(Constant.BATCH_DATASOURCE)
    @ConfigurationProperties(prefix = "datasource.batch.hikari")
    public DataSource batchDataSource() {
        HikariDataSource batchDataSource = DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
        batchDataSource.setReadOnly(true);
        return batchDataSource;
    }
}
