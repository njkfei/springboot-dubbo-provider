package com.sanhao.tech.data.service.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sanhao.tech.sevice.model.User;

/**
 * 用户表相关操作
 * @author sanhao
 *
 */
public interface UserDAO {
	// 获取用户余额
	@Select("select `user_balance` from `ysyy_user` where user_id = #{user_id}")
	BigDecimal getBalance(@Param("user_id") int user_id);
	
	// 更新用户余额
	@Update("UPDATE ysyy_user SET user_balance =  #{user_balance}  WHERE user_id =  #{user_id}")
	int setBalance(@Param("user_id") int user_id,@Param("user_balance") BigDecimal user_balance);
	
	// 获取学生的用户编号
	@Select("SELECT user_id FROM ysyy_user_student WHERE student_session=#{student_session}")
	int getUserIdOfStudent(@Param("student_session")String student_session);
	
	// 获取老师的用户编号
	@Select("SELECT user_id FROM ysyy_user_teacher WHERE teacher_session=#{teacher_session}")
	int getUserIdOfTeacher(@Param("teacher_session")String teacher_session);
	
	// 获取老师的用户编号
	@Select("SELECT user_id FROM ysyy_user_parent WHERE parent_session=#{parent_session}")
	int getUserIdOfParent(@Param("parent_session")String parent_session);
	
	// 获取老师的用户编号
	@Select("SELECT user_id FROM ysyy_user_recommend WHERE recommend_session=#{recommend_session}")
	int getUserIdOfRecommend(@Param("recommend_session")String recommend_session);
	
	@Select("select `user_id` from `ysyy_user_teacher` where `teacher_id` = #{teacher_id}")
	int getUserTeacherId(@Param("teacher_id") int teacher_id);
	
	@Select("select teacher_id from ysyy_user_teacher where user_id = #{user_id}")
	int getTeacherId(@Param("user_id")int user_id);
	
	@Select("SELECT * FROM `ysyy_user` WHERE `user_recommend`=#{user_recommend}")
	List<User> getUsersByRecommend(@Param("user_recommend") int user_recommend);
	
	@Select("SELECT user_id FROM `ysyy_user` WHERE `user_recommend`=#{user_recommend}")
	List<Integer> getUserIdsByRecommend(@Param("user_recommend") int user_recommend);
	
	@Select("select * from ysyy_user where user_id=#{user_id} and user_type=#{user_type}")
	User getUser(@Param("user_id")int user_id,@Param("user_type")int user_type);
	
	@Select("SELECT `student_name` FROM `ysyy_user_student` WHERE user_id=#{user_id}")
	String getStudentName(@Param("user_id")int user_id);
}
