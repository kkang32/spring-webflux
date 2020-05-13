package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.CustomR2dbcTransactionManager;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
@EnableTransactionManagement
public class TransactionConfig extends AbstractR2dbcConfiguration {

	
	@Bean
	public DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
		return DatabaseClient.builder().connectionFactory(connectionFactory).build();
	}
	
	@Override
	public ConnectionFactory connectionFactory() {
		 ConnectionFactory connectionFactory =
		 ConnectionFactories.get("r2dbc:mysql://localhost:3306/r2dbctest?useUnicode=true&characterEncoding=utf8");
		 return connectionFactory;

//		ConnectionFactory connectionFactory = new H2ConnectionFactory(
//				H2ConnectionConfiguration.builder().url("mem:testdb;DB_CLOSE_DELAY=-1;").username("sa").build());
//	
//
//		DatabaseClient client = DatabaseClient.builder().connectionFactory(connectionFactory).build();
//
//		
//		Mono<Void> completion = client.execute("CREATE TABLE user (\r\n" + 
//				"	USER_ID VARCHAR(50) NULL DEFAULT NULL,\r\n" + 
//				"	USER_NAME VARCHAR(50) NULL DEFAULT NULL,\r\n" + 
//				"	AGE INT(11) NULL DEFAULT NULL,\r\n" + 
//				"	IP VARCHAR(50) NULL DEFAULT NULL\r\n" + 
//				");")
//		        .then();
//		
//		return connectionFactory;
	}

	@Bean
	ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
		return new CustomR2dbcTransactionManager(connectionFactory);
	}

}
