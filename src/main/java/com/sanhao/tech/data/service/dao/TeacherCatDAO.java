package com.sanhao.tech.data.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.UnknownTypeHandler;

import com.sanhao.tech.sevice.model.CatBaseInfo;

public interface TeacherCatDAO {
	@Insert("insert into ysyy_teacher_cat(teacher_id,cat_id) values(#{teacher_id},#{cat_id})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "ytc_id")  
	int insetTc(@Param("teacher_id")int teacher_id,@Param("cat_id")int cat_id );
	
	@Delete("delete from ysyy_teacher_cat where teacher_id = #{teacher_id}")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "ytc_id")  
	int deleteTc(@Param("teacher_id")int teacher_id );
	
	@Select("select ytc.cat_id,yc.cat_name from ysyy_teacher_cat ytc, ysyy_cat yc  where ytc.cat_id=yc.cat_id and ytc.teacher_id=#{teacher_id} ")
	 @Results(value = {  
	   @Result(property="cat_id",column="cat_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER,typeHandler=UnknownTypeHandler.class),  
	   @Result(property="cat_name", column="cat_name",javaType=String.class,jdbcType=JdbcType.VARCHAR) 
	 }) 
	List<CatBaseInfo> queryTc(@Param("teacher_id")int teacher_id);
}
