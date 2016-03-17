package com.sanhao.tech.data.service.serviceImpl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.sanhao.tech.data.service.dao.UserDAO;
import com.sanhao.tech.sevice.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	/*
	 * 1 教师 2 学生 3 家长 4 推荐人
	 */
	public int getUserId(String session_id, int type) {
		// TODO Auto-generated method stub
		switch (type) {
		case 1:
			return userDAO.getUserIdOfTeacher(session_id);
		case 2:
			return userDAO.getUserIdOfStudent(session_id);
		case 3:
			return userDAO.getUserIdOfParent(session_id);
		case 4:
			return userDAO.getUserIdOfRecommend(session_id);
		default:
			return 0;
		}
	}

	public BigDecimal getBalance(int user_id) {
		// TODO Auto-generated method stub
		return userDAO.getBalance(user_id);
	}

	public int updateBalance(int user_id, BigDecimal user_balance) {
		// TODO Auto-generated method stub
		return userDAO.setBalance(user_id, user_balance);
	}
}
