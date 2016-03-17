package com.sanhao.tech.data.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sanhao.tech.sevice.model.TeacherTag;

public interface TeacherTagDAO {
	@Insert("insert into ysyy_teacher_tag(teacher_id,tag_id) values(#{teacher_id},#{tag_id})")
	int insertTt(@Param("teacher_id")int teacher_id,@Param("tag_id")int tag_id);
	
	@Select("select tt.ytt_id,tt.teacher_id,tt.tag_id,t.tag_name from ysyy_teacher_tag tt, ysyy_tag t where tt.teacher_id = #{teacher_id} and tt.tag_id=t.tag_id")
	List<TeacherTag> getTtList(@Param("teacher_id")int teacher_id);
	
	@Delete("delete from ysyy_teacher_tag where teacher_id=#{teacher_id}")
	int deleteTt(@Param("teacher_id")int teacher_id);
}
