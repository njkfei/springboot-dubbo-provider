package com.sanhao.tech.data.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sanhao.tech.sevice.model.StudentLabel;
import com.sanhao.tech.sevice.model.TeacherStuLabel;

public interface TeacherStuLabelDAO {
	@Select("select t.yts_id,t.teacher_id,l.label_name from ysyy_teacher_stulabel t,ysyy_student_label l where teacher_id=#{teacher_id} and l.label_id=t.label_id"
			)
	List<TeacherStuLabel> getTeacherStuLableList(@Param("teacher_id")int teacher_id);
	
	@Insert("insert into ysyy_teacher_stulabel(teacher_id,label_id) values(#{teacher_id},#{label_id})")
	int addTeacherStuLabel(@Param("teacher_id")int teacher_id,@Param("label_id")int label_id);
	
	@Delete("delete from ysyy_teacher_stulabel where teacher_id=#{teacher_id}")
	int deleteTeacherStuLabel(@Param("teacher_id")int teacher_id);
	
	@Select("select label_id,label_name from ysyy_student_label order by label_id asc limit #{limit},#{count}")
	List<StudentLabel> getStudentLabelList(@Param("limit")int limit,@Param("count")int count);
}
