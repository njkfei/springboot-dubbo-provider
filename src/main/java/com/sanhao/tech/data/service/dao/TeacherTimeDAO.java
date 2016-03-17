package com.sanhao.tech.data.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.sanhao.tech.sevice.model.TeacherTime;

public interface TeacherTimeDAO {
	@Select("select teacher_id,tt_time,free_num,timemap1,timemap2,timemap3 from ysyy_teacher_time where teacher_id=#{teacher_id}")
	List<TeacherTime> getTtList(@Param("teacher_id")int teacher_id);
	
	@Update("update ysyy_teacher_time set free_num=#{free_num},timemap1=#{timemap1},timemap2=#{timemap2},timemap3=#{timemap3} where teacher_id=#{teacher_id} and tt_time=#{tt_time}")
	int updateTt(@Param("teacher_id")int teacher_id,@Param("tt_time")int tt_time,@Param("free_num")int free_num,@Param("timemap1")int timemap1,@Param("timemap2")int timemap2,@Param("timemap3")int timemap3);

	 @Results(value = {  
	   @Result(property="teacher_id",column="teacher_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),  
	   @Result(property="tt_time", column="tt_time",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
	   @Result(property="free_num", column="free_num",javaType=Short.class,jdbcType=JdbcType.TINYINT),
	   @Result(property="timemap1", column="timemap1",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
	   @Result(property="timemap2", column="timemap2",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
	   @Result(property="timemap3",column="timemap3",javaType=Integer.class,jdbcType=JdbcType.INTEGER)
	 }) 
	@Update("update ysyy_teacher_time set free_num=#{free_num},timemap1=#{timemap1},timemap2=#{timemap2},timemap3=#{timemap3} where teacher_id=#{teacher_id} and tt_time=#{tt_time}")
	int updateTtByTt(TeacherTime tt);

	
	@Update("update ysyy_teacher_time set free_num=#{free_num} where teacher_id=#{teacher_id} and tt_time=#{tt_time}")
	int updateTt2(@Param("teacher_id")int teacher_id,@Param("tt_time")int tt_time,@Param("free_num")int free_num);

	
	@Insert("insert into ysyy_teacher_time(teacher_id,tt_time,free_num,timemap1,timemap2,timemap3) values(#{teacher_id},#{tt_time},#{free_num},#{timemap1},#{timemap2},#{timemap3})")
	int intsertTt(@Param("teacher_id")int teacher_id,@Param("tt_time")int tt_time,@Param("free_num")int free_num,@Param("timemap1")int timemap1,@Param("timemap2")int timemap2,@Param("timemap3")int timemap3);
	
	List<TeacherTime> getTeacherTimeList(@Param("teacher_id")int teacher_id,@Param("list") List<Integer>tt_times);
	
	List<TeacherTime> getTeacherTimeListFree(@Param("teacher_id")int teacher_id,@Param("list") List<Integer>tt_times);
	
	@Select("select teacher_id,tt_time,free_num,timemap1,timemap2,timemap3 from ysyy_teacher_time where teacher_id=#{teacher_id} limit 1" )
	TeacherTime getTeacherTime(@Param("teacher_id")int teacher_id,@Param("tt_time")int tt_time);
}
