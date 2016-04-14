package com.sanhao.tech.data.service.Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.sanhao.tech.data.service.config.CacheConfig;
import com.sanhao.tech.data.service.config.MybatisConfiguration;
import com.sanhao.tech.data.service.config.OrderServiceConfig;
import com.sanhao.tech.data.service.controller.UserController;


@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@Import({OrderServiceConfig.class,MybatisConfiguration.class,UserController.class,CacheConfig.class})
public class App 
{
    public static void main( String[] args )
    {
    	 SpringApplication.run(App.class, args);
    }
}
