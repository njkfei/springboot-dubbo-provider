package com.sanhao.tech.data.service.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sanhao.tech.sevice.model.Coupon;
import com.sanhao.tech.sevice.model.CouponBuyInfo;
import com.sanhao.tech.sevice.model.CouponTemplate;

public interface CouponDAO {
	@Select("SELECT `coupon_id`, `coupon_no`, `coupon_status`, `coupon_from`, `coupon_begintime`, `coupon_endtime`,"
			+ " `template_id`, `order_id` FROM `ysyy_coupon` WHERE coupon_id=#{coupon_id}")
	Coupon getCoupon(@Param("coupon_id") int coupon_id);
	
	@Select("SELECT `template_id`, `template_name`, `template_value`, "
			+ "`template_type`, `template_status`, `template_minfee`, `template_minhour`, "
			+ "`template_createtime`, `template_fee`, `template_hour`,"
			+ " `template_begintime`, `template_endtime`, `template_model`,"
			+ " `template_teacher` FROM `ysyy_coupon_template` WHERE template_id=#{template_id}")
	CouponTemplate getCouponTemplate(@Param("template_id") int template_id);

	@Select("SELECT `buy_id`, `coupon_id`, `user_id`, `buy_time`, `buy_status` FROM `ysyy_coupon_buy` WHERE coupon_id=#{coupon_id} AND user_id=#{user_id}")
	CouponBuyInfo getCouponBuyInfo(@Param("coupon_id")int coupon_id, @Param("user_id")int user_id);
	
	@Select("SELECT `buy_id`, `coupon_id`, `user_id`, `buy_time`, `buy_status` FROM `ysyy_coupon_buy` WHERE coupon_id=#{coupon_id}")
	CouponBuyInfo getCouponBuyInfoByCouponId(@Param("coupon_id")int coupon_id);
	
	@Insert("INSERT INTO `ysyy_coupon_buy`( `coupon_id`, `user_id`, `buy_time`, `buy_status`) VALUES (#{coupon_id},#{user_id},#{buy_time},#{buy_status})")
	int insertCouponBuy(CouponBuyInfo couonBuyInfo);

	// 通过活动编号，获取活动模板列表
	@Select("SELECT `template_id`, `template_name`, `template_value`, `template_type`,"
			+ " `template_status`, `template_minfee`, `template_minhour`, `template_createtime`, `template_fee`, "
			+ "`template_hour`, `template_begintime`,"
			+ "`template_endtime`, `template_model`, `template_teacher` FROM `ysyy_coupon_template` WHERE template_model=#{template_model}")
	List<CouponTemplate> getCouponTemplateListByModel(@Param("template_model")int template_model);

	@Update("update `ysyy_coupon` set `coupon_status` = #{coupon_status} where `order_id` =  #{order_id}")
	int updateCouponStatus(@Param("order_id")int order_id,@Param("coupon_status")short coupon_status);
	
	@Update("update `ysyy_coupon` set `coupon_status` = #{coupon_status},`coupon_begintime` = #{coupon_begintime},`coupon_endtime` = #{coupon_endtime} where `coupon_id` =  #{coupon_id}")
	int updateCouponStatusAndTimeByCouponId(@Param("coupon_id")int coupon_id,@Param("coupon_status")short coupon_status,@Param("coupon_begintime")int coupon_begintime,@Param("coupon_endtime")int template_endtime);

	@Update("update `ysyy_coupon` set `coupon_status` = #{coupon_status} where `coupon_id` =  #{coupon_id}")
	int updateCouponStatusByCouponId(@Param("coupon_id")int coupon_id,@Param("coupon_status")short coupon_status);

	@Select("SELECT `template_value` FROM `ysyy_coupon_template` WHERE `template_id`=#{template_id}")
	BigDecimal getTemplateValue(@Param("template_id")int template_id);
	
	@Select("SELECT `coupon_id` FROM `ysyy_coupon` WHERE order_id=#{order_id}")
	List<Integer> getCouponIds(@Param("order_id") int order_id);
	
	@Select("SELECT `coupon_id` FROM `ysyy_coupon` WHERE order_id=#{order_id} limit 1")
	Integer getCouponId(@Param("order_id") int order_id);
	
	@Select("SELECT * FROM `ysyy_coupon_buy` WHERE `buy_status`=#{buy_status}")
	List<CouponBuyInfo> getCouponBuyInfos(@Param("buy_status") int buy_status);
	
	@Select("SELECT `coupon_id`, `coupon_no`, `coupon_status`, `coupon_from`, `coupon_begintime`, `coupon_endtime`,"
			+ " `template_id`, `order_id` FROM `ysyy_coupon` WHERE coupon_status=#{coupon_status}")
	List<Coupon> getCouponsByCouponStatus(@Param("coupon_status") int coupon_status);
	
	@Select("SELECT `coupon_id`, `coupon_no`, `coupon_status`, `coupon_from`, `coupon_begintime`, `coupon_endtime`,"
			+ " `template_id`, `order_id` FROM `ysyy_coupon` WHERE template_id=#{template_id} AND coupon_status=#{coupon_status} limit #{limit}")
	List<Coupon> getCouponsOfTemplateByStatus(@Param("template_id")int template_id,@Param("coupon_status") int coupon_status,@Param("limit")int limit);
}
