package com.xx.jersey.demo.main;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.jdbc.Driver;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.ws.rs.BeanParam;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by hzxiongxin on 2016/11/16.
 */
@Component
@Configuration
@ComponentScan(basePackages = {"com.xx.jersey.demo"})
@EnableTransactionManagement
public class SpringJavaConfiguration {

    //配置druid数据库连接池
    @Bean(autowire = Autowire.BY_TYPE)
    public DruidDataSource getDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/demo?useUnicode=true&amp;characterEncoding=utf-8&amp;autoReconnect=true&amp;failOverReadOnly=false&amp;maxReconnects=10");//设置数据库连接
        dataSource.setUsername("root");
        dataSource.setPassword("");
        try {
            dataSource.setDriver(new Driver());//设置数据库驱动
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSource.setTestWhileIdle(true);//申请连接时检测
        try {
            dataSource.setFilters("stat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setValidationQuery("select 1");//设置检测连接时使用的sql语句
        dataSource.setMaxActive(100);//设置最大连接数
        dataSource.setInitialSize(10);//初始化连接数
        dataSource.setMaxWait(20000);//最长等待时间，20秒
        return dataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean getSqlSessionFactory(){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(
                getDataSource()
        );//设置数据源
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));//设置mybatis配置文件路径
        return  sqlSessionFactoryBean;
    }

    //设置mapper扫描配置
    @Bean(name = "mapper",autowire=Autowire.BY_NAME)
    public MapperScannerConfigurer getMapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.xx.jersey.demo.dao");//设置mapper地址
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");//对应上面的getSqlSessionFactory方法的Bean name
        return mapperScannerConfigurer;
    }

    @Bean
    public PlatformTransactionManager getTransactionManager(){
        return new DataSourceTransactionManager(getDataSource());
    }

}
