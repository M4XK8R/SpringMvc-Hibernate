package com.maxkor.users.data.config;

import java.util.Objects;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan("com.maxkor.users.data")
@EnableTransactionManagement
public class DatabaseConfig {

  private final Environment environment;

  public DatabaseConfig(Environment environment) {
    this.environment = environment;
  }


  @Bean
  protected DataSource getDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(
        Objects.requireNonNull(
            environment.getProperty("db.driver")
        )
    );
    dataSource.setUrl(environment.getProperty("db.url"));
    dataSource.setUsername(environment.getProperty("db.username"));
    dataSource.setPassword(environment.getProperty("db.password"));
    return dataSource;
  }

  @Bean
  protected LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
    LocalContainerEntityManagerFactoryBean factoryBean
        = new LocalContainerEntityManagerFactoryBean();
    factoryBean.setDataSource(getDataSource());
    factoryBean.setJpaProperties(getHibernateProperties());
    factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    factoryBean.setPackagesToScan("com.maxkor.users.data.model");
    return factoryBean;
  }

  @Bean
  protected PlatformTransactionManager getTransactionManager(
      EntityManagerFactory entityManagerFactory
  ) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory);
    return transactionManager;
  }

  /*
  Private
   */
  private Properties getHibernateProperties() {
    Properties properties = new Properties();
    properties.put(
        "hibernate.dialect",
        environment.getProperty("hibernate.dialect")
    );
    properties.put(
        "hibernate.hbm2ddl.auto",
        environment.getProperty("hibernate.hbm2ddl.auto")
    );
    properties.put(
        "hibernate.show_sql",
        environment.getProperty("hibernate.show_sql")
    );
    return properties;
  }
}
