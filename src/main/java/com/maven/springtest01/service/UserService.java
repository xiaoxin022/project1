package com.maven.springtest01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maven.springtest01.dao.LoginLogDao;
import com.maven.springtest01.dao.UserDao;
import com.maven.springtest01.domain.LoginLog;
import com.maven.springtest01.domain.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private LoginLogDao loginLogDao;
	
	public boolean hasMatchUser(String userName,String password){
		int matchCount = userDao.getMatchCount(userName, password);
		return matchCount>0;
	}
	public User findUserByUserName(String userName){
		return userDao.findUserByUserName(userName);
	}
	public void loginSuccess(User user){
		user.setCredits(user.getCredits()+15);
		LoginLog loginLog = new LoginLog();
		loginLog.setUserId(user.getUserId());
		loginLog.setIp(user.getLastIP());
		loginLog.setLoginDate(user.getLastVisit());
		userDao.updateLoginInfo(user);
		loginLogDao.insertLoginLog(loginLog);
		
	}
}
