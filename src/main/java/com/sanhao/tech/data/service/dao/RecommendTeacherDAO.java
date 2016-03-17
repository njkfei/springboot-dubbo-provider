package com.sanhao.tech.data.service.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sanhao.tech.sevice.model.RecommendTeacher;

/**
 * 访问推荐老师数据库
 * @author sanhao
 *
 */

public interface RecommendTeacherDAO {
	@Select("SELECT `rt_id`, `teacher_id`, `rt_type`, `cat_id`, `tag_id`, `rt_order`, `channel_id`, `rt_img`, `rt_time`, `rt_desc` FROM `ysyy_recommend_teacher` WHERE teacher_id=#{teacher_id}")
	RecommendTeacher getRecommendTeacher(@Param("teacher_id")int teacher_id);
}
