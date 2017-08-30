package com.maven.springtest01.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.maven.springtest01.domain.LoginLog;

@Repository
public class LoginLogDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void insertLoginLog(LoginLog loginLog){
		String sqlStr = " INSERT INTO T_LOGIN_LOG(USER_ID,IP,LOGIN_DATETIME) " + " VALUES(?,?,?) ";
		Object[] args = {loginLog.getUserId(),loginLog.getIp(),loginLog.getLoginDate()};
		jdbcTemplate.update(sqlStr,args);
	}
}
