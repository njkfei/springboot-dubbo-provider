package com.sanhao.tech.data.service.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;


/**
 * ref: http://dubbo.io/User+Guide-zh.htm
 * @author sanhao
 *
 */

@Configuration
@EnableAutoConfiguration
public class DubboBaseConfig {

	@Bean
	public ApplicationConfig application() {
		ApplicationConfig application = new ApplicationConfig();
		application.setName("demoService");

		return application;
	}

	// 连接注册中心
	@Bean
	public RegistryConfig registry() {
		RegistryConfig registry = new RegistryConfig();
		registry.setProtocol("zookeeper");                       // 也可以是其它注册中心
		registry.setAddress("zookeeper://127.0.0.1:2181");
/*		registry.setUsername("aaa");
		registry.setPassword("bbb");*/

		// 不向注册中心注册
/*		问题：
		为方便开发测试，经常会在线下共用一个所有服务可用的注册中心，这时，如果一个正在开发中的服务提供者注册，可能会影响消费者不能正常运行。
		解决方案：
		可以让服务提供者开发方，只订阅服务(开发的服务可能依赖其它服务)，而不注册正在开发的服务，通过直连测试正在开发的服务。*/
		registry.setRegister(false);
		
		return registry;
	}
	
	@Bean
	public List<RegistryConfig> registries(){
		List<RegistryConfig> registries = new ArrayList<RegistryConfig>();
		
		registries.add(registry());
		
		return registries;
	}
	
	// 服务提供者协议配置，可以配置多协议
	@Bean
	public ProtocolConfig protocol(){
	ProtocolConfig protocol = new ProtocolConfig();
	protocol.setName("dubbo");
	protocol.setPort(20880);
	protocol.setThreads(200);
	
	return protocol;
	}
}
