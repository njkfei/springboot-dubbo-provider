package com.sanhao.tech.data.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.sanhao.tech.data.service.dao.TeacherAlbumDAO;
import com.sanhao.tech.data.service.dao.TeacherDAO;
import com.sanhao.tech.data.service.dao.TeacherTimeDAO;
import com.sanhao.tech.sevice.model.Teacher;
import com.sanhao.tech.sevice.model.TeacherAlbum;
import com.sanhao.tech.sevice.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{
	@Autowired private TeacherAlbumDAO teacherAlbumDAO;
	@Autowired private TeacherDAO teacherDAO;
	@Autowired private TeacherTimeDAO teacherTimeDAO;
	
	public List<TeacherAlbum> getTaList(int teacher_id, int limit, int count) {
		// TODO Auto-generated method stub
		return teacherAlbumDAO.getTaList(teacher_id, limit, count);
	}

	public int addTa(int teacher_id, String ta_name, String ta_img, short ta_order) {
		// TODO Auto-generated method stub
		return teacherAlbumDAO.addTa(teacher_id, ta_name, ta_img, ta_order);
	}

	public TeacherAlbum getTaDetail(int ta_id) {
		// TODO Auto-generated method stub
		return teacherAlbumDAO.getTaDetail(ta_id);
	}

	public int editTa(int ta_id, String ta_name, String ta_img, short ta_order) {
		// TODO Auto-generated method stub
		return teacherAlbumDAO.editTa(ta_id, ta_name, ta_img, ta_order);
	}

	public int deleteTa(int ta_id) {
		// TODO Auto-generated method stub
		return teacherAlbumDAO.deleteTa(ta_id);
	}

	public Teacher getTeacher(int teacher_id) {
		// TODO Auto-generated method stub
		return teacherDAO.getTeacher(teacher_id);
	}
}
