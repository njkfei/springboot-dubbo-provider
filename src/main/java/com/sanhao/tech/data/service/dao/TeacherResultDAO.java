package com.sanhao.tech.data.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sanhao.tech.sevice.model.TeacherResult;

public interface TeacherResultDAO {
	@Select("select tr_id,teacher_id,tr_time,tr_desc from ysyy_teacher_result where teacher_id = #{teacher_id}")
	List<TeacherResult> getTrList(@Param("teacher_id")int teacher_id);
	
	@Insert("insert into ysyy_teacher_result(teacher_id,tr_time,tr_desc) values(#{teacher_id},#{tr_time},#{tr_desc})")
	int insertTr(@Param("teacher_id")int teacher_id,@Param("tr_time")int tr_time,@Param("tr_desc")String tr_desc);
	
	@Delete("delete from ysyy_teacher_result where teacher_id=#{teacher_id}")
	int deleteTr(@Param("teacher_id")int teacher_id);
	
	int editTr(@Param("tr_id")int tr_id,@Param("tr_time")int tr_time,@Param("tr_desc")String tr_desc);
}
