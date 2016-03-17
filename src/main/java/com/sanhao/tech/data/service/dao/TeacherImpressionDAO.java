package com.sanhao.tech.data.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.sanhao.tech.sevice.model.TeacherImpression;

public interface TeacherImpressionDAO {
	
	@Select("select tm.teacher_id,tm.impression_id,tm.ti_time,im.impression_name "
			+ "FROM ysyy_teacher_impression tm,ysyy_impression im "
			+ "WHERE tm.impression_id=im.impression_id "
			+ "AND tm.teacher_id = #{teacher_id} "
			+ "order by tm.teacher_id asc, tm.impression_id asc limit #{limit},#{count}")
	@Results(value={
			@Result(property="teacher_id", column="teacher_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
			@Result(property="impression_id", column="impression_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
			@Result(property="ti_time", column="ti_time",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
			@Result(property="impression_name", column="impression_name",javaType=String.class,jdbcType=JdbcType.VARCHAR)
	})
	List<TeacherImpression> getTiList(@Param("teacher_id")int teacher_id,@Param("limit")int limit,@Param("count")int count);
	
	@Insert("insert into ysyy_teacher_impression(teacher_id,impression_id,ti_time) VALUES(#{teacher_id},#{impression_id},#{ti_time})") 
	int insertTi(@Param("teacher_id")int teacher_id,@Param("impression_id")short impression_id,@Param("ti_time")int ti_time);
	
	@Delete("delete from ysyy_teacher_impression where teacher_id=#{teacher_id}")
	int deleteTi(@Param("teacher_id")int teacher_id);
}
