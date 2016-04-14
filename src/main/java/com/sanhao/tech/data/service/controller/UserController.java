package com.sanhao.tech.data.service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sanhao.tech.data.service.config.CacheConfig;
import com.sanhao.tech.data.service.dao.UserDAO;
import com.sanhao.tech.sevice.model.User;


@RestController
@EnableAutoConfiguration
@Cacheable
public class UserController {
	
	@Autowired
	private UserDAO userDAO;
	
	@Cacheable(value="userCache",key="#user_id")// 使用了一个缓存名叫 userCache 
	@RequestMapping("/user/{user_id}")
	@ResponseBody
	User getUser(@PathVariable int user_id){
		User user = userDAO.getUser(user_id, 2);
		System.out.println("get user from DB user : " + user);
		return user;
	}
	
	@Cacheable(value="userCache",key="#user_id")// 使用了一个缓存名叫 userCache 
	@RequestMapping("/user2/{usre_id}")
	@ResponseBody
	User getUser2(@PathVariable int user_id){
		User user = new User();
		user.setUser_id(user_id);
		user.setUser_account(15810534204L);
		System.out.println("get user2 from local user : " + user);
		return user;
	}

/*	public static void main(String[] args){
		SpringApplication.run(SampleController.class,args);
	}*/
}
