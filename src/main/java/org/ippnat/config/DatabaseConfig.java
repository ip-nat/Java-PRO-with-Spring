package org.ippnat.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.ippnat.config.properties.DatabaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    private final DatabaseProperties databaseProperties;

    public DatabaseConfig(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(databaseProperties.getUrl());
        hikariConfig.setUsername(databaseProperties.getUsername());
        hikariConfig.setPassword(databaseProperties.getPassword());
        hikariConfig.setDriverClassName(databaseProperties.getDriverClassName());
        hikariConfig.setConnectionTimeout(databaseProperties.getConnectionTimeout());
        hikariConfig.setMinimumIdle(databaseProperties.getMinimumIdle());
        hikariConfig.setMaximumPoolSize(databaseProperties.getMaximumPoolSize());
        return new HikariDataSource(hikariConfig);
    }
}