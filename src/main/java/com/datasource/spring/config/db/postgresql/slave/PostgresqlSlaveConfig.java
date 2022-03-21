package com.datasource.spring.config.db.postgresql.slave;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * <pre>
 * postgresql slave DB 접속 클래스(읽기 전용)
 * </pre>
 */
@Configuration
public class PostgresqlSlaveConfig {
	private final String driverClassName;
	private final String jdbcUrl;
	private final String username;
	private final String password;

	public PostgresqlSlaveConfig(@Value("${spring.datasource.postgres.slave.driver-class-name}") String driverClassName
			, @Value("${spring.datasource.postgres.slave.jdbc-url}") String jdbcUrl
			, @Value("${spring.datasource.postgres.slave.username}") String username
			, @Value("${spring.datasource.postgres.slave.password}") String password) {
		this.driverClassName = driverClassName;
		this.jdbcUrl = jdbcUrl;
		this.username = username;
		this.password = password;
	}

	/**
	 * <pre>
	 * slave DB datasource 반환 메서드
	 * </pre>
	 *
	 * @return slave db 접속 datasource 객체
	 */
	@Bean("postgresqlSlaveDataSource")
	public DataSource postgresqlSlaveDataSource() {
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setPoolName("pg-slave");
		hikariDataSource.setDriverClassName(this.driverClassName);
		hikariDataSource.setUsername(this.username);
		hikariDataSource.setJdbcUrl(this.jdbcUrl);
		hikariDataSource.setPassword(this.password);

		return hikariDataSource;
	}
}
