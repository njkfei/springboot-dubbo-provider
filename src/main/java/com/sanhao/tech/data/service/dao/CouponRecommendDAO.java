package com.sanhao.tech.data.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sanhao.tech.sevice.model.CouponRecommend;

public interface CouponRecommendDAO {
	@Select("SELECT * FROM `ysyy_coupon_recommend` WHERE `user_recommend`=#{user_recommend} ")
	List<CouponRecommend> getCouponRecommend(@Param("user_recommend")int user_recommend);
	
	@Insert("INSERT INTO `ysyy_coupon_recommend`(`coupon_id`,  `user_id`, `user_recommend`, `recommend_time`) "
			+ "VALUES (#{coupon_id},#{user_id},#{user_recommend},#{recommend_time})")
	int insertCouponRecommend(CouponRecommend couponRecommend);
/*	
	@Update("UPDATE `ysyy_coupon_recommend` SET `lock_state`=#{lock_state} WHERE `coupon_id`=#{coupon_id}")
	int updateLockState(@Param("coupon_id")int coupon_id,@Param("lock_state")int lock_state);*/
}
