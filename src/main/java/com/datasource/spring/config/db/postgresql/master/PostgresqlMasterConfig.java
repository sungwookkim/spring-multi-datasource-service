package com.datasource.spring.config.db.postgresql.master;

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
 * postgresql master DB 접속 클래스(등록, 수정, 삭제 전용)
 * </pre>
 *
 */
@Configuration
public class PostgresqlMasterConfig {
	private final String driverClassName;
	private final String jdbcUrl;
	private final String username;
	private final String password;

	public PostgresqlMasterConfig(@Value("${spring.datasource.postgres.master.driver-class-name}") String driverClassName
			, @Value("${spring.datasource.postgres.master.jdbc-url}") String jdbcUrl
			, @Value("${spring.datasource.postgres.master.username}") String username
			, @Value("${spring.datasource.postgres.master.password}") String password) {
		this.driverClassName = driverClassName;
		this.jdbcUrl = jdbcUrl;
		this.username = username;
		this.password = password;
	}

	/**
	 * <pre>
	 * master DB datasource 반환 메서드
	 * </pre>
	 *
	 * @return master db 접속 datasource 객체
	 */
	@Bean("postgresqlMasterDataSource")
	public DataSource postgresqlMasterDataSource() {
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setPoolName("pg-master");
		hikariDataSource.setDriverClassName(this.driverClassName);
		hikariDataSource.setUsername(this.username);
		hikariDataSource.setJdbcUrl(this.jdbcUrl);
		hikariDataSource.setPassword(this.password);

		Resource initSchema = new ClassPathResource("schema.sql");
		Resource initData = new ClassPathResource("data.sql");
		DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, initData);

		DatabasePopulatorUtils.execute(databasePopulator, hikariDataSource);

		return hikariDataSource;
	}
}
