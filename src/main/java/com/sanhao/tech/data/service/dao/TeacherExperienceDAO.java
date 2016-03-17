package com.sanhao.tech.data.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.UnknownTypeHandler;

import com.sanhao.tech.sevice.model.TeacherExperience;

public interface TeacherExperienceDAO {
	@Select("select te_id, teacher_id, te_starttime, te_endtime, te_desc from ysyy_teacher_experience"
			+ " where teacher_id = #{teacher_id} order by te_starttime asc,te_id desc limit #{limit}, #{count}")
	 @Results(value = {  
			   @Result(property="te_id",column="te_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER,typeHandler=UnknownTypeHandler.class),  
			   @Result(property="teacher_id", column="teacher_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
			   @Result(property="te_starttime", column="te_starttime",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
			   @Result(property="te_endtime", column="te_endtime",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
			   @Result(property="te_desc", column="te_desc",javaType=String.class,jdbcType=JdbcType.VARCHAR)
			 }) 
	List<TeacherExperience> getTeList(@Param("teacher_id")int teacher_id,@Param("limit")int limit,@Param("count")int count);
	    
    @Insert("insert ysyy_teacher_experience (teacher_id, te_starttime, te_endtime, te_desc) "
    		+ " values (#{teacher_id}, #{te_starttime}, #{te_endtime},#{te_desc})")
    int addTe(@Param("teacher_id")int teacher_id,@Param("te_starttime")int te_starttime,@Param("te_endtime")int te_endtime,@Param("te_desc")String te_desc);
    
    
    
    @Select("select te_id,teacher_id, te_starttime, te_endtime, te_desc "
    		+ "from ysyy_teacher_experience where te_id = #{te_id}")
    TeacherExperience getTeDetail(@Param("te_id")int te_id);
    
    //@UpdateProvider(type = TeacherSqlProvider.class, method = "editTeSql")
    int editTe(@Param("te_id")int te_id,@Param("te_starttime")int te_starttime,@Param("te_endtime")int te_endtime,@Param("te_desc")String te_desc);
    
    @Delete("delete from  ysyy_teacher_experience where te_id = #{te_id}")
    int deleteTe(@Param("te_id")int te_id);
}
