package com.sanhao.tech.data.service.dao;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.sanhao.tech.sevice.model.TeacherAlbum;


public interface TeacherAlbumDAO {

	List<TeacherAlbum> getTaList(@Param("teacher_id")int teacher_id,@Param("limit")int limit ,@Param("count")int count);

	@Insert("insert into ysyy_teacher_album(teacher_id,ta_name,ta_img,ta_order) values(#{teacher_id},#{ta_name},#{ta_img},#{ta_order})")
	//@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "ta_id")  
	int addTa(@Param("teacher_id")int teacher_id,@Param("ta_name")String ta_name,@Param("ta_img")String ta_img ,@Param("ta_order")short ta_order);
	

	TeacherAlbum getTaDetail(int ta_id);
	

	int editTa(@Param("ta_id")int ta_id,@Param("ta_name")String ta_name,@Param("ta_img")String ta_img ,@Param("ta_order")short ta_order);
	

	int deleteTa(int ta_id);

}
