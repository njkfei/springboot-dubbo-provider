package com.sanhao.tech.data.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sanhao.tech.sevice.model.Bill;
import com.sanhao.tech.sevice.model.FeeLog;

public interface FeeLogDAO {

	@Select("SELECT * FROM `ysyy_admin_feelog` WHERE `oct_id` = #{oct_id} limit 1")
	FeeLog getFeeLog(@Param("oct_id")int oct_id);
}
