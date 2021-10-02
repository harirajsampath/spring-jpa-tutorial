package com.bita.training.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.bita.training.data")
@EntityScan(basePackages = "com.bita.training.model")
@EnableTransactionManagement
@PropertySource("classpath:com/bita/training/application.properties")
@EnableJpaAuditing
public class JPAConfig {

	@Value("${db.jdbcUrl}")
	public String jdbcUrl;
	@Value("${db.username}")
	public String username;
	@Value("${db.password}")
	public String password;
	@Value("${db.cachePrepStmst}")
	public String cachePrepStmst;
	@Value("${db.prepStmtCacheSize}")
	public String prepStmtCacheSize;
	@Value("${db.prepStmtCacheSqlLimit}")
	public String prepStmtCacheSqlLimit;

	
	 @Bean
	  public DataSource dataSource() {
		 HikariConfig config = new HikariConfig();
		 config.setJdbcUrl(jdbcUrl);
		 config.setUsername(username);
		 config.setPassword(password);
		 config.setDriverClassName("com.mysql.jdbc.Driver");
		 config.addDataSourceProperty("cachePrepStmts", cachePrepStmst);
		 config.addDataSourceProperty("prepStmtCacheSize", prepStmtCacheSize);
		 config.addDataSourceProperty("prepStmtCacheSqlLimit", prepStmtCacheSqlLimit);
		 HikariDataSource ds = new HikariDataSource(config);
		return ds;
	  }

	  @Bean
	  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setGenerateDdl(true);

	    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	    factory.setJpaVendorAdapter(vendorAdapter);
	    factory.setPackagesToScan(new String[] {"com.bita.training.data", "com.bita.training.model"});
	    factory.setDataSource(dataSource());
	    return factory;
	  }

	  @Bean
	  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

	    JpaTransactionManager txManager = new JpaTransactionManager();
	    txManager.setEntityManagerFactory(entityManagerFactory);
	    return txManager;
	  }
}
