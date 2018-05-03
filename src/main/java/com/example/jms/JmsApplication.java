package com.example.jms;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import java.util.Properties;

@SpringBootApplication
@ComponentScan
@EnableJms
public class JmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmsApplication.class, args);
	}

	@Bean
	public Properties properties() {
		Properties properties = new Properties();
		properties.setProperty("URL", "jdbc:postgresql://localhost:5432/postgres");
		properties.setProperty("user", "postgres");
		properties.setProperty("password", "password");

		return properties;
	}

	@Bean
	public AtomikosDataSourceBean dataSource() {
		AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
		dataSource.setXaDataSourceClassName("org.postgresql.xa.PGXADataSource");
		dataSource.setXaProperties(properties());
		dataSource.setUniqueResourceName("postgresDatasource");

		return dataSource;
	}

	@Bean
	public UserTransactionManager jtaTransactionManager() {
		return new UserTransactionManager();
	}

	@Bean
	public UserTransactionImp jtaUserTransaction() throws SystemException {
		UserTransactionImp userTransactionImp = new UserTransactionImp();
		userTransactionImp.setTransactionTimeout(300);

		return userTransactionImp;
	}

	@Bean
	public JtaTransactionManager transactionManager() throws SystemException {
		JtaTransactionManager transactionManager = new JtaTransactionManager();
		transactionManager.setTransactionManager(jtaTransactionManager());
		transactionManager.setUserTransaction(jtaUserTransaction());

		return transactionManager;
	}
}
