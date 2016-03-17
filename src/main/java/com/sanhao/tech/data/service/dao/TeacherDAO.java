package com.sanhao.tech.data.service.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sanhao.tech.sevice.model.Teacher;

/**
 * 老师信息接口
 * @author sanhao
 *
 */
public interface TeacherDAO {
	@Select("SELECT `teacher_id`, `user_id`, `teacher_nickname`, `teacher_mobile`, `teacher_mail`, `teacher_password`, "
			+ "`teacher_name`, `teacher_sex`, `teacher_birth_year`, `teacher_birth_month`, `teacher_birth_day`, `teacher_idcard`, "
			+ "`teacher_address`, `teacher_school`, `teacher_course_cat`, `teacher_course_tag`, `teacher_pic`, `teacher_auth_url`, "
			+ "`teacher_auth_url2`, `teacher_seniority`, `teacher_title`, `teacher_award`, `teacher_desc`, `teacher_level`, `teacher_star`, "
			+ "`teacher_qq`, `teacher_session`, `teacher_map`, `teacher_gender`, `teacher_constellation`, `teacher_zodiac`, `teacher_character`, "
			+ "`teacher_hobby`, `teacher_feature`, `teacher_sanhao`, `teacher_ascendency`, `teacher_longitude`, `teacher_latitude`, `teacher_bgimg`,"
			+ " `teacher_bgcolor`, `teacher_realname_url`, `teacher_realname_auth`, `teacher_auth_time`, `teacher_qualification`, `teacher_title_auth`, "
			+ "`teacher_certification_url`, `teacher_certification_auth`, `teacher_student_num`, `teacher_hours_num`, `teacher_pageview`, `teacher_organization`,"
			+ " `teacher_identity`, `teacher_degree`, `teacher_info_step`, `sub_time`, `area_id`, `verify_time`, `update_time` FROM `ysyy_user_teacher` WHERE teacher_id=#{teacher_id}")
	Teacher getTeacher(@Param("teacher_id")int teacher_id);
	
	// 获取用户编号
	@Select("SELECT  `user_id` FROM `ysyy_user_teacher` WHERE teacher_id=#{teacher_id}")
	int getUserId(@Param("teacher_id")int teacher_id);
	
	@Select("SELECT  `teacher_name` FROM `ysyy_user_teacher` WHERE `teacher_id`=#{teacher_id}")
	String getTeacherName(@Param("teacher_id")int teacher_id);
	
	@Select("SELECT  `user_id` FROM `ysyy_user_teacher` WHERE `teacher_id`=#{teacher_id}")
	Integer getUserIdByTeacherId(@Param("teacher_id")int teacher_id);
}
