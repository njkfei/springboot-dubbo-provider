package com.sanhao.tech.data.service.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.sanhao.tech.data.service.controller.UserController;

/**
 * Hello world!
 *
 */
@Configuration
@EnableCaching
@EnableAutoConfiguration
public class CacheConfig 
{
  
	
  @Bean
  public SimpleCacheManager cacheManager(){
	  SimpleCacheManager cacheManager = new SimpleCacheManager();
	  cacheManager.setCaches(Arrays.asList(userCache(),defaultCache()));
	  return cacheManager;
  }
  
  @Bean(name="userCache")
  public ConcurrentMapCache userCache(){
	  ConcurrentMapCache accountCache = new ConcurrentMapCache("userCache");
	  return  accountCache;
  }
  
  @Bean
  public ConcurrentMapCache defaultCache(){
	  ConcurrentMapCache defaultCache = new ConcurrentMapCache("defaultCache");
	  return  defaultCache;
  }
}
