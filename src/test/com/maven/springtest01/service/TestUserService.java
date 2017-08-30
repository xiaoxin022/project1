package com.maven.springtest01.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; 

import com.maven.springtest01.domain.User;


@RunWith(SpringJUnit4ClassRunner.class)//基于JUnit4的spring测试框架
@ContextConfiguration(locations={"/applicationContext.xml"})//启动spring容器
public class TestUserService {

	@Autowired
	private UserService userService;
	@Test
	public void hasMatchUser(){
		 boolean b1 = userService.hasMatchUser("admin", "123456");
		 boolean b2 = userService.hasMatchUser("admin", "1111");
		 assertTrue(b1);
		 assertTrue(!b2);
	}
	
	@Test
	public void findUserByUserName(){
		User user = userService.findUserByUserName("admin");
		assertEquals(user.getUserName(), "admin");
	}
	@Test
	public void loginSuccess(){
		User user =userService.findUserByUserName("admin");
		userService.loginSuccess(user);
	}
}
