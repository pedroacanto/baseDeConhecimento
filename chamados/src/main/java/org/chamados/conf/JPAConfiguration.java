package org.chamados.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"org.chamados"})
@EnableTransactionManagement
public class JPAConfiguration {

    @Bean(destroyMethod = "close")
    DataSource dataSource(Environment env){
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName("com.mysql.jdbc.Driver");
        dataSourceConfig.setJdbcUrl("jdbc:mysql://localhost:3306/chamados_totvs");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("rabbit");

        return new HikariDataSource(dataSourceConfig);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("org.chamados");

        Properties jpaProperties = new Properties();

        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.show_sql", "true");

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;


//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//
//        factoryBean.setJpaVendorAdapter(vendorAdapter);
//
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUsername("root");
//        dataSource.setPassword("rabbit");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/chamados_totvs");
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//
//        factoryBean.setDataSource(dataSource);
//
//        Properties props = new Properties();
//        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//        props.setProperty("hibernate.show_sql", "true");
//        props.setProperty("hibernate.hbm2ddl.auto", "update");
//
//        factoryBean.setJpaProperties(props);
//
//        factoryBean.setPackagesToScan("org.chamados.models");
//
//        return factoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

}