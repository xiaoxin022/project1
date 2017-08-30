package com.maven.springtest01.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.maven.springtest01.domain.User;

@Repository
// 通过spring注解定义一个DAO
public class UserDao {

	@Autowired
	// 自动注入 JdbcTemplate 的 Bean
	private JdbcTemplate jdbcTemplate;

	public int getMatchCount(String userName, String password) {
		String sqlStr = " SELECT COUNT(*) FROM T_USER "
				+ " WHERE USER_NAME=? AND PASSWORD=? ";
		return jdbcTemplate.queryForObject(sqlStr, new Object[] { userName,
				password }, Integer.class);
	}

	public User findUserByUserName(final String userName) {
		String sqlStr = " SELECT USER_ID,USER_NAME,CREDITS "
				+ " FROM T_USER WHERE USER_NAME=? ";
		final User user = new User();
		jdbcTemplate.query(sqlStr, new Object[] { userName },
				new RowCallbackHandler() {

					public void processRow(ResultSet rs) throws SQLException {
						user.setUserId(rs.getInt("user_id"));
						user.setUserName(userName);
						user.setCredits(rs.getInt("credits"));
					}
				});
		return user;
	}

	public void updateLoginInfo(User user) {
		String sqlStr = " UPDATE T_USER SET LAST_VISIT=?,LAST_IP=?,CREDITS=? "
				+ " WHERE USER_ID=? ";
		jdbcTemplate.update(
				sqlStr,
				new Object[] { user.getLastVisit(), user.getLastIP(),
						user.getCredits(), user.getUserId() });
	}
}
