package org.chamados.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "org.chamados.repositories", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
@EnableTransactionManagement
public class JPAConfiguration {

   @Autowired
   private Environment environment;

   @Value("${datasource.chamados.maxPoolSize:10}")
   private int maxPoolSize;

   @Bean
   @Primary
   @ConfigurationProperties(prefix = "datasource.chamados")
   public DataSourceProperties dataSourceProperties(){
      return new DataSourceProperties();
   }

   @Bean(destroyMethod = "close")
   DataSource dataSource(){
      DataSourceProperties dataSourceProperties = dataSourceProperties();
      HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder
              .create(dataSourceProperties.getClassLoader())
              .driverClassName(dataSourceProperties.getDriverClassName())
              .url(dataSourceProperties.getUrl())
              .username(dataSourceProperties.getUsername())
              .password(dataSourceProperties.getPassword())
              .type(HikariDataSource.class)
              .build();
      dataSource.setMaximumPoolSize(maxPoolSize);
      return dataSource;
   }

   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
      LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
      factoryBean.setDataSource(dataSource());
      factoryBean.setPackagesToScan(new String[] {"org.chamados.models"});
      factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
      factoryBean.setJpaProperties(jpaProperties());
      return factoryBean;
   }

   @Bean
   public JpaVendorAdapter jpaVendorAdapter(){
      HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      return vendorAdapter;
   }

   @Bean
   @Autowired
   public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
      JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
      jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
      return jpaTransactionManager;
   }

   private Properties jpaProperties(){
      Properties properties = new Properties();
      properties.put("hibernate.dialect", environment.getRequiredProperty("datasource.chamados.hibernate.dialect"));
      properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("datasource.chamados.hibernate.hbm2ddl.method"));
      properties.put("hibernate.show_sql", environment.getRequiredProperty("datasource.chamados.hibernate.show_sql"));
      properties.put("hibernate.format_sql", environment.getRequiredProperty("datasource.chamados.hibernate.format_sql"));
      if(StringUtils.isNotEmpty(environment.getRequiredProperty("datasource.chamados.defaultSchema"))){
         properties.put("hibernate.default_schema", environment.getRequiredProperty("datasource.chamados.defaultSchema"));
      }
      return properties;
   }

}