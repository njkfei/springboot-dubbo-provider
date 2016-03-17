package com.sanhao.tech.data.service.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.sanhao.tech.data.service.serviceImpl.OrderServiceImpl;
import com.sanhao.tech.sevice.service.OrderService;
import com.sanhao.tech.data.service.config.DubboBaseConfig;

/**
 * ref: http://dubbo.io/User+Guide-zh.htm
 * @author sanhao
 *
 */

@Configuration
@EnableAutoConfiguration
@Import(DubboBaseConfig.class)
public class OrderServiceConfig {
	@Autowired 
	DubboBaseConfig dubboBaseConfig;

	@Bean
	public OrderService orderService(){
		OrderService orderService = new OrderServiceImpl();
		return orderService;
	}
	
	// 当前应用配置

	 
	// 服务提供者暴露服务配置
	@Bean
	public ServiceConfig<OrderService> service(){
	ServiceConfig<OrderService> service = new ServiceConfig<OrderService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
	service.setApplication(dubboBaseConfig.application());
	service.setRegistry(dubboBaseConfig.registry()); // 多个注册中心可以用setRegistries(),service.setRegistries(registries());
	service.setProtocol(dubboBaseConfig.protocol()); // 多个协议可以用setProtocols()
	
	service.setInterface(OrderService.class);
	service.setVersion("1.0.0");
	service.setRef(orderService());
	service.setCluster("failsafe");  // 集群配置模式，可以是failover,failfast,failsave,failback等，根据需求场景选择合适的配置
	service.export();
	
	// service.setGroup("XXX"); //设置分组
	
	return service;
	}
}
