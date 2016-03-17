package com.sanhao.tech.data.service.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sanhao.tech.sevice.model.Pay;

public interface PayDAO {
	@Select("SELECT pay_id,order_id,pay_method,pay_url,pay_status,pay_addtime,pay_time,pay_total,"
			+ "pay_balance,pay_pay,user_id,user_type,pay_type,coupon_id from ysyy_pay WHERE pay_id=#{pay_id} limit 1")
	Pay getPay(@Param("pay_id")int pay_id);
}
