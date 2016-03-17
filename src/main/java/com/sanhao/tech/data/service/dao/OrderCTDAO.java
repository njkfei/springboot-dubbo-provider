package com.sanhao.tech.data.service.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sanhao.tech.sevice.model.OrderCT;
import com.sanhao.tech.sevice.model.OrderCTDetail;
import com.sanhao.tech.sevice.model.OrderCTList;

public interface OrderCTDAO {

	@Select("SELECT `oct_id`, `order_id`, `teacher_id`, `course_id`, `oct_status`,"
			+ " `oct_fee`, `act_id`, `oct_type`, `start_time_id`, `tt_num`, `end_time_id`, "
			+ "`tt_time`, `actual_start_timestamp`, "
			+ "`actual_end_timestamp`, `valid_time`, `valid_tt_num`, `valid_fee`,`teacher_fee`,"
			+ " `course_approve_time` FROM `ysyy_order_ct` WHERE order_id=#{order_id}")
	List<OrderCT> getOrderCTList(@Param("order_id")int order_id);
	
	@Insert("INSERT INTO ysyy_order_ct (order_id, teacher_id, course_id, oct_status, oct_fee,"
			+ " oct_type, tt_num, start_time_id, end_time_id, tt_time) "
			+ "VALUES(#{order_id},#{teacher_id},#{course_id},#{oct_status},#{oct_fee},#{oct_type},#{tt_num},"
			+ "#{start_time_id},#{end_time_id},#{tt_time}) ")
	int insertOrderCT(OrderCT oct);
	
	@Update("update `ysyy_order_ct` set `oct_status` = #{oct_status} where `oct_id`=#{oct_id}")
	int updateOrderCT(@Param("oct_id")int oct_id,@Param("oct_status") short oct_status);
	
	@Update("update `ysyy_order_ct` set `oct_fee` = #{oct_fee} where `oct_id`=#{oct_id}")
	int updateOrderCTFee(@Param("oct_id")int oct_id,@Param("oct_fee") BigDecimal oct_fee);
	
	@Select("SELECT `oct_id`, `oct_fee` FROM `ysyy_order_ct` WHERE order_id=#{order_id}")
	List<OrderCT> getOctKeyInfoList(@Param("order_id")int order_id);
	

	@Select("SELECT tt_time FROM ysyy_order_ct WHERE oct_status = #{oct_status} AND order_id= #{order_id} ORDER BY tt_time")
	List<Integer> getTtList(@Param("order_id")int order_id,@Param("oct_status")int oct_status);

	@Select("SELECT  `oct_id`, `oct_fee` FROM `ysyy_order_ct` WHERE oct_id=#{oct_id}")
	OrderCT getOrderCTFee(@Param("oct_id")int oct_id);
	
	@Select("SELECT `oct_id`, `order_id`, `teacher_id`, `course_id`, `oct_status`,"
			+ " `oct_fee`, `act_id`, `oct_type`, `start_time_id`, `tt_num`, `end_time_id`, "
			+ "`tt_time`, `actual_start_timestamp`, "
			+ "`actual_end_timestamp`, `valid_time`, `valid_tt_num`, `valid_fee`,`teacher_fee`,"
			+ " `course_approve_time` FROM `ysyy_order_ct` WHERE oct_id=#{oct_id}")
	OrderCT getOrderCT(@Param("oct_id") int oct_id);
	
	@Select("SELECT sum(oct_fee) FROM ysyy_order_ct WHERE oct_status = #{oct_status} AND teacher_id = #{teacher_id}")
	BigDecimal getOrderByStatus(@Param("teacher_id")int teacher_id,@Param("oct_status")int oct_status);
		
	@Select("SELECT sum(`tt_num`) as total_oct_tt FROM `ysyy_order_ct` WHERE `order_id`= #{order_id} AND oct_status <> 3 ")
	int order_getTotalTT(@Param("order_id")int order_id);

	// to do 
	int order_validAllTT(int teacher_id, List<Integer> oct_status_list);

	
    @Insert("INSERT INTO `ysyy_order_ct` "
    		+ "(`order_id`, `teacher_id`, `course_id`, `oct_status`, `oct_fee`, `oct_type`, `tt_num`, `start_time_id`, `end_time_id`, `tt_time`) "
    		+ "VALUES (#{order_id},#{teacher_id}, #{course_id}, #{oct_status }, #{oct_fee}, #{oct_type}, #{tt_num}, #{start_time_id}, #{end_time_id}, #{tt_time}")
	int oct_reserve(int order_id, int teacher_id, int course_id, int oct_status, BigDecimal oct_fee,
			BigDecimal oct_type, int tt_num, int start_time_id, int end_time_id, int tt_time);
    
    @Select("select `oct_id` from `ysyy_order_ct`  'where `teacher_id` = #{teacher_id} and `order_id` = #{order_id } and `tt_time`= #{tt_time}' and `oct_status`= #{status}" )
	int oct_check(int order_id, int teacher_id, int tt_time, int status);
	
	@Select("SELECT oct.oct_id, oct.order_id, oct.oct_status, oct.oct_fee, oct.oct_type, oct.tt_time, oct.tt_num,"
			+ " oct.actual_start_timestamp,oct.actual_end_timestamp,oct.valid_time,oct.valid_tt_num,oct.valid_fee , "
            + " start_time_id, end_time_id,  oct.course_id, c.course_name, c.cat_id, c.tag_id, c.course_price, cat.cat_name, tag.tag_name, "
            + " oct.teacher_id, t.teacher_name, t.teacher_level,c.course_status " 
            + " FROM ysyy_order_ct oct, ysyy_order o, ysyy_course c, ysyy_user_teacher t,  ysyy_cat cat, ysyy_tag tag " 
            + " WHERE o.course_id = c.course_id and o.teacher_id = t.teacher_id "
            + " AND c.cat_id = cat.cat_id and c.tag_id = tag.tag_id "
            + " AND o.order_id = oct.order_id and oct.order_id = #{order_id}  ORDER BY oct.tt_time ASC")
	List<OrderCTList> oct_list(@Param("order_id") int order_id);

	@Select("SELECT oct.oct_id, oct.order_id, oct.oct_status, oct.oct_fee, oct.oct_type, oct.tt_time, oct.tt_num,'o.`user_id` "
            + "start_time_id, end_time_id,o.course_id, c.course_name, c.cat_id, c.tag_id, c.course_price, cat.cat_name, tag.tag_name, "
            + "o.teacher_id, t.teacher_name, t.teacher_level, oct.`actual_start_timestamp`, oct.`actual_end_timestamp`, oct.`valid_time`, oct.`valid_tt_num` "
            + " FROM ysyy_order_ct oct, ysyy_order o, ysyy_course c, ysyy_user_teacher t,  ysyy_cat cat, ysyy_tag tag "
            + " WHERE o.course_id = c.course_id and o.teacher_id = t.teacher_id AND c.cat_id = cat.cat_id and c.tag_id = tag.tag_id "
            + " AND o.order_id = oct.order_id and oct.oct_id = #{oct_id}")
	OrderCTDetail oct_detail(int oct_id);
} 
