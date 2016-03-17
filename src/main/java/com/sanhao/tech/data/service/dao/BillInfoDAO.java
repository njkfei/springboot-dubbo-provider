package com.sanhao.tech.data.service.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sanhao.tech.sevice.model.BillInfo;

public interface BillInfoDAO {

	@Select("SELECT * from ysyy_bill_info where id=#{id}")
	BillInfo getBillInfo(@Param("id") int id);
	
	@Select("SELECT * from ysyy_bill_info where order_id=#{order_id} and `user_type`=#{user_type} and detail_type=1 order by time limit 1")
	BillInfo getBillInfoByOrderId(@Param("order_id") int order_id,@Param("user_type")int user_type);
	
	@Select("SELECT * from ysyy_bill_info where user_id=#{user_id} and `user_type`=#{user_type} and detail_type=1")
	BillInfo getBillInfoByUserId(@Param("user_id") int user_id,@Param("user_type")int user_type);
	
	@Insert("INSERT INTO `ysyy_bill_info`(`bill_id`,`user_id`, `user_type`, `income_type`, `time`, `bill_type`,`course_name`,`user_name`, `fee_amount`, `fee_reback`, `fee_actual`, `bill_state`, `detail_type`, `detail_id`,`order_id`)"
			+ " VALUES (#{bill_id},#{user_id}, #{user_type}, #{income_type}, #{time}, #{bill_type}, #{course_name},#{user_name},#{fee_amount}, #{fee_reback}, #{fee_actual}, #{bill_state}, #{detail_type}, #{detail_id},#{order_id})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")  
	int insertBillInfo(BillInfo billInfo);
	
	@Update("UPDATE `ysyy_bill_info` "
			+ "SET `user_id`=#{user_id},`user_type`=#{user_type},`income_type`=#{income_type},`time`=#{time},"
			+ "`bill_type`=#{bill_type},`fee_amount`=#{fee_amount},`fee_reback`=#{fee_reback},`fee_actual`=#{fee_actual},`bill_state`=#{bill_state},"
			+ "`detail_type`=#{detail_type},`detail_id`=#{detail_id} WHERE `id`=#{id}")
	int updateBillInfo(BillInfo billInfo);
	
	@Update("UPDATE `ysyy_bill_info` "
			+ "SET `user_id`=#{user_id},`user_type`=#{user_type},`income_type`=#{income_type},`time`=#{time},"
			+ "`bill_type`=#{bill_type},`fee_amount`=#{fee_amount},`fee_reback`=#{fee_reback},`fee_actual`=#{fee_actual},`bill_state`=#{bill_state},"
			+ "`detail_type`=#{detail_type},`detail_id`=#{detail_id} WHERE `order_id`=#{order_id} and `id`=#{id} and detail_type=1" )
	int updateBillInfoByOrderId(BillInfo billInfo);
	
	
	@Select("SELECT * from ysyy_bill_info where user_id=#{user_id} and `bill_state`=#{bill_state} and `detail_id`=#{detail_id} limit 1")
	BillInfo getBillInfoByAcId(@Param("user_id") int user_id,@Param("bill_state")int bill_state,@Param("detail_id")int detail_id);

	@Select("SELECT IFNULL(`bill_id`,0) FROM `ysyy_bill_detail` order by `bill_id` desc limit 1")
	Integer getLastBillId();
}
