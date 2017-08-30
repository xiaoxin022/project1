package com.maven.springtest01.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.maven.springtest01.domain.User;
import com.maven.springtest01.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	//负责处理/index.html的请求
	@RequestMapping(value="/index.html")
	public String loginPage(){
		System.out.println("return login");
		return "login";
	}
	@RequestMapping(value="/loginCheck.html")
	public ModelAndView loginCheck(HttpServletRequest request,LoginCommand loginCommand){
		boolean isValidUser = userService.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());
		if(!isValidUser){
			return new ModelAndView("login","error","用户名密码错误");
		}else{
			User user = userService.findUserByUserName(loginCommand.getUserName());
			user.setLastIP(request.getRemoteAddr());//设置登录IP
			user.setLastVisit(new Date());//设置登录时间
			userService.loginSuccess(user);
			request.getSession().setAttribute("user", user);//设置session
			return new ModelAndView("main");
		}
	}
}
