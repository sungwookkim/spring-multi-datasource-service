package com.datasource.spring.config.db.postgresql.routing;

import com.datasource.spring.config.mybatis.type.LocalDateTimeTypeHandler;
import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * postgresql routing 설정 클래스
 * </pre>
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.datasource.repo.mybatis"}, sqlSessionFactoryRef = "postgresqlSessionFactory")
public class PostgresqlRoutingConfig {
	private final DataSource postgresqlMasterDataSource;
	private final DataSource postgresqlSlaveDataSource;

	public PostgresqlRoutingConfig(DataSource postgresqlMasterDataSource
			, DataSource postgresqlSlaveDataSource) {
		this.postgresqlMasterDataSource = new Log4jdbcProxyDataSource(postgresqlMasterDataSource) ;
		this.postgresqlSlaveDataSource = new Log4jdbcProxyDataSource(postgresqlSlaveDataSource);
	}

	/**
	 * <pre>
	 * postgresql db routing 설정 메서드
	 * </pre>
	 *
	 * @return 분기 db 정보가 포함되어 있는 routing datasource 객체
	 */
	@Bean
    public DataSource routingDataSource() {
        ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("write", postgresqlMasterDataSource);
        dataSourceMap.put("read", postgresqlSlaveDataSource);

        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(postgresqlMasterDataSource);

        return routingDataSource;
    }
    
	/**
	 * <pre>
	 * routing datasoruce를 LazyConnectionDataSourceProxy로 반환하는 메서드
	 * 
	 * routing datasource에 분기 db를 사용하기 위해선 db 연결 시 마다 어떤 db에 접속 할지 판단해야 하는데
	 * 이를 위해 LazyConnectionDataSourceProxy를 사용하여 lazy loading를 사용한다.
	 * </pre>
	 *
	 * @param routingDataSource 분기 db 정보가 포함되어 있는 routing datasource
	 * @return lazy loading을 사용하는 LazyConnectionDataSourceProxy 객체
	 */
    @Bean
    public DataSource dataSource(DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

    /**
     * <pre>
     * db와 mybatis를 연결하기 위한 SqlSessionFactory 객체 생성 메서드
     * 
     * 이때 사용되는 datasource는 LazyConnectionDataSourceProxy를 통해 생성된 datasource를 사용하였다.
     * </pre>
     *
     * @return 
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory postgresqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.datasource.repo.mybatis");

        sqlSessionFactoryBean.getObject().getConfiguration()
                .getTypeHandlerRegistry().register(LocalDateTime.class, new LocalDateTimeTypeHandler()); // LocalDateTime Mapping 을 위한 typeHandler 추가
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);

        return sqlSessionFactoryBean.getObject();
    }

    /**
     * <pre>
     * SqlSessionTemplate 객체를 반환하는 메서드
     * </pre>
     * 
     * @param postgresqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionTemplate postgresSessionTemplate(SqlSessionFactory postgresqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(postgresqlSessionFactory);
    }
    
    /**
     * <pre>
     * 트랜잭션 객체 반환
     * 
     * 이때 사용되는 datasource는 LazyConnectionDataSourceProxy를 통해 생성된 datasource를 사용하였다.
     * </pre>
     * 
     * @return DataSourceTransactionManager 객체 반환
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);

        return transactionManager;
    }    
}
