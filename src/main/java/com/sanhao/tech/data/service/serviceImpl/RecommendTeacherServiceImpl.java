package com.sanhao.tech.data.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.sanhao.tech.data.service.dao.RecommendTeacherDAO;
import com.sanhao.tech.sevice.model.RecommendTeacher;
import com.sanhao.tech.sevice.service.RecommendTeacherService;

@Service
public class RecommendTeacherServiceImpl implements RecommendTeacherService {
	@Autowired private RecommendTeacherDAO recommendTeacherDAO;
	
	public RecommendTeacher getRecommendTeacher(int teacher_id) {
		return recommendTeacherDAO.getRecommendTeacher(teacher_id);
	}

}
