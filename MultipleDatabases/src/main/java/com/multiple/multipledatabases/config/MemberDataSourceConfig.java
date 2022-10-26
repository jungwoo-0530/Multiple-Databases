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
    basePackages = "com.multiple.multipledatabases.repository.db1",
    entityManagerFactoryRef = "memberEntityManagerFactory",
    transactionManagerRef = "memberTransactionManager"
)
@EnableTransactionManagement
public class MemberDataSourceConfig {
  @Bean
  @Primary // 메인으로 사용될 DataSource
  @ConfigurationProperties("spring.datasource1")
  public DataSourceProperties memberDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary // 메인으로 사용될 DataSource
  @ConfigurationProperties("spring.datasource1.hikari")
  public HikariDataSource memberDataSource() {
    return memberDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
  }


  @Bean
  @Primary // 메인으로 사용될 DataSource
  public LocalContainerEntityManagerFactoryBean memberEntityManagerFactory() {

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true);
    vendorAdapter.setShowSql(true);
//        Properties props = new Properties();  // Properties에 Hibernate Config 설정 추가
//        props.setProperty("hibernate.format_sql", String.valueOf(true));
//        props.setProperty("hibernate.hbm2ddl.auto", "create");
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("com.multiple.multipledatabases.domain.db1");
    factory.setDataSource(memberDataSource());
//        factory.setJpaProperties(props);
    return factory;
  }

  @Bean
  @Primary // 메인으로 사용될 DataSource
  public PlatformTransactionManager memberTransactionManager(@Qualifier("memberEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory);
    return txManager;
  }

}
