package com.sanhao.tech.data.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sanhao.tech.sevice.model.Bill;

public interface BillDAO {

	@Select("SELECT * from ysyy_bill where bill_id=#{bill_id}")
	Bill getBillInfo(@Param("bill_id") int bill_id);
	
	@Insert("insert into `ysyy_bill` (`user_id`, `user_type`, `bill_income`, `bill_rank`, `bill_type`, `course_name`,`user_name`,`bill_amount`, `__id`, `bill_remark`,`bill_time) "
			+ "VALUES(#{user_id},#{user_type},#{bill_income},#{bill_rank},#{bill_type},#{course_name},#{user_name},#{bill_amount},#{__id},#{bill_remark},#{bill_time})")
	int insertBill(Bill bill);
	

	@Select("SELECT * from ysyy_bill where bill_id > #{bill_id} limit #{count}")
	List<Bill> getBillList(@Param("bill_id") int bill_id,@Param("count")int count);

	@Select("SELECT * from ysyy_bill where user_id = #{user_id}")
	List<Bill> getBillListByUserId(@Param("user_id") int user_id);
}
