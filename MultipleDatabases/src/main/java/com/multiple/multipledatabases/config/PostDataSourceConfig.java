package com.multiple.multipledatabases.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

/**
 * fileName     : PostDataSourceConfig
 * author       : jungwoo
 * description  :
 */
@Configuration
@EnableJpaRepositories(
    basePackages = "com.multiple.multipledatabases.repository.db2",
    entityManagerFactoryRef = "postEntityManagerFactory",
    transactionManagerRef = "postTransactionManager"
)
@EnableTransactionManagement
public class PostDataSourceConfig {
  @Bean
  @ConfigurationProperties("spring.datasource2")
  public DataSourceProperties postDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @ConfigurationProperties("spring.datasource2.hikari")
  public HikariDataSource postDataSource() {
    return postDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
  }


  @Bean
  public LocalContainerEntityManagerFactoryBean postEntityManagerFactory() {

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("com.multiple.multipledatabases.domain.db2");
    factory.setDataSource(postDataSource());
    factory.setPersistenceUnitName("postEntityManager");  // EntityManager 직접 사용시 가져오기 위한 Name 설정
    return factory;
  }

  @Bean
  public PlatformTransactionManager postTransactionManager(@Qualifier("postEntityManagerFactory") EntityManagerFactory entityManagerFactory) {

    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory);
    return txManager;
  }

}
