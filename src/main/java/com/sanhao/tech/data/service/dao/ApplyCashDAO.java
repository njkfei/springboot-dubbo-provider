package com.sanhao.tech.data.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sanhao.tech.sevice.model.ApplyCash;
import com.sanhao.tech.sevice.model.Bill;

public interface ApplyCashDAO {

	@Select("SELECT * FROM `ysyy_apply_cash` WHERE `ac_id`=#{ac_id}")
	ApplyCash getApplyCash(@Param("ac_id")int ac_id);
}
