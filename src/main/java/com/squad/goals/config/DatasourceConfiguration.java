package com.squad.goals.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
public class DatasourceConfiguration {

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSourceProperties mssqlDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource mssqlDataSource() {
		return mssqlDataSourceProperties().initializeDataSourceBuilder().build();
	}
}
