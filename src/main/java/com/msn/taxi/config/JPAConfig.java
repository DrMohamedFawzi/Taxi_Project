package com.msn.taxi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class JPAConfig {
    public static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";
    private static final String USERNAME = "postgres";
    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/taxi";

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(POSTGRESQL_DRIVER);
        dataSource.setUrl(CONNECTION_URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(Env.DB_PASSWORD);

        return dataSource;
    }
}
