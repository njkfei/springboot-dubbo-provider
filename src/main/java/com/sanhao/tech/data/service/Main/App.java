package com.sanhao.tech.data.service.Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.sanhao.tech.data.service.config.CouponServiceConfig;
import com.sanhao.tech.data.service.config.MybatisConfiguration;
import com.sanhao.tech.data.service.config.OrderServiceConfig;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@Import({OrderServiceConfig.class,MybatisConfiguration.class,CouponServiceConfig.class})
public class App 
{
    public static void main( String[] args )
    {
    	 SpringApplication.run(App.class, args);
    }
}
