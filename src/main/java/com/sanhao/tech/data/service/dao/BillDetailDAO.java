package com.sanhao.tech.data.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sanhao.tech.sevice.model.BillDetail;
import com.sanhao.tech.sevice.model.BillInfo;

public interface BillDetailDAO {

	@Insert("INSERT INTO `ysyy_bill_detail`(`bill_id`,`order_id`, `oct_id`, `user_id`, `user_type`, `time`, `fee_amount`, `fee_reback`, `fee_actual`, `pay_type`, `tt_left`)"
			+ " VALUES ( #{bill_id},#{order_id}, #{oct_id}, #{user_id}, #{user_type}, #{time}, #{fee_amount}, #{fee_reback}, #{fee_actual}, #{pay_type}, #{tt_left})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")  
	int insertBillDetail(BillDetail billDetail);
	
	@Select("SELECT * FROM `ysyy_bill_detail` WHERE `order_id`=#{order_id}")
	List<BillDetail> getBillDetails(@Param("order_id")int order_id);
	
	// 获取剩余课时
	@Select("SELECT IFNULL(`tt_left`,0) FROM `ysyy_bill_detail` WHERE `order_id`=#{order_id} and `user_id`=#{user_id} order by time desc limit 1")
	Integer getTtLeft(@Param("order_id")int order_id,@Param("user_id")int user_id);
}
