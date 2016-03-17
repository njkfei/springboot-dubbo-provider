package com.sanhao.tech.data.service.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sanhao.tech.sevice.model.BillLog;

public interface BillLogDAO {

	@Select("SELECT * from bill_log where log_id=#{log_id}")
	BillLog getBillLogInfo(@Param("log_id") int log_id);
	
	@Select("SELECT * from bill_log where user_id=#{user_id}")
	BillLog getBillLogInfoByUserId(@Param("user_id") int user_id);
	
	@Insert("insert into `bill_log` (`user_id`, `user_type`, `time`, `come_type`,`bill_type`, `income`, `fee_reback`, `platform_fee`,`actual_income',`state`) "
			+ "VALUES(#{user_id},#{user_type},#{time},#{come_type},#{bill_type},#{income},#{fee_reback},#{platform_fee},#{actual_income},#{state})")
	int insertBillLog(BillLog billLog);
	
}
