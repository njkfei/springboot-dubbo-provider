package com.sanhao.tech.data.service.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sanhao.tech.sevice.model.Course;

/**
 * 课程详情信息访问DAO
 * @author sanhao
 *
 */
public interface CourseDAO {

	@Select("SELECT * FROM ysyy_course where course_id=#{course_id}")
	Course getCourseInfo(@Param("course_id")int course_id);
	
	@Select("SELECT `course_name` FROM ysyy_course where course_id=#{course_id}")
	String getCourseName(@Param("course_id")int course_id);
	
}
