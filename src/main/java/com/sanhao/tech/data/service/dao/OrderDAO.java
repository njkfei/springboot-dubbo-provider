package com.sanhao.tech.data.service.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sanhao.tech.sevice.model.CourseTime;
import com.sanhao.tech.sevice.model.Order;
import com.sanhao.tech.sevice.model.OrderDetail;

/**
 * 访问订单数据库接口
 * @author sanhao
 *
 */
public interface OrderDAO {
	@Select("SELECT `course_id`, `order_id`, `order_no`, `user_id`, `user_type`, `order_time`, `order_notice`, `order_remark`, `order_status`, `order_paytime`,"
			+ " `order_act`, `order_fee`, `order_type`, `price`, `teacher_id`, `tt_num`, `order_confirm` FROM `ysyy_order` WHERE  order_id=#{order_id}")
	Order getOrder(@Param("order_id")int order_id);
	
	@Select("SELECT order_id FROM `ysyy_order` WHERE  order_no=#{order_no}")
	int getOrderIdByOrderNO(@Param("order_no")String order_no);
			
	@Select("SELECT sum(`oct_fee`) as wait FROM `ysyy_order_ct` WHERE `teacher_id`=#{teacher_id} and `oct_status` =1")
	BigDecimal getFeeWaitClass(@Param("teacher_id") int teacher_id);
	
	//6完成结算  9全部支付 10部分支付
	@Select("SELECT sum(`oct_fee`) as wait FROM `ysyy_order_ct` WHERE `teacher_id`=#{teacher_id} and `oct_status` in(6,9,10)")
	BigDecimal getFeeDone(@Param("teacher_id") int teacher_id);
	
	// 添加订单
	@Insert("INSERT INTO ysyy_order(`order_no`, `user_id`, `user_type`, `order_time`, "
			+ "`order_status`, `order_type`,`teacher_id`, "
			+ " `course_id`, `order_fee`, `price`, `tt_num`) "
			+ "values(#{order_no},#{user_id},#{user_type},#{order_time},"
			+ "#{order_status},#{order_type},#{teacher_id},"
			+ "#{course_id},#{order_fee},#{price},#{tt_num})")
	// 下面的内容没有，也可以正常运行起来
//	 @Results(value = {  
//			   @Result(property="order_no",column="order_no",javaType=Integer.class,jdbcType=JdbcType.INTEGER),  
//			   @Result(property="user_id", column="user_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
//			   @Result(property="user_type", column="user_type",javaType=Short.class,jdbcType=JdbcType.TINYINT),
//			   @Result(property="order_time", column="order_time",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
//			   @Result(property="order_status", column="order_status",javaType=Short.class,jdbcType=JdbcType.TINYINT),
//			   @Result(property="order_type",column="order_type",javaType=Short.class,jdbcType=JdbcType.TINYINT),  
//			   @Result(property="teacher_id", column="teacher_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
//			   @Result(property="course_id", column="course_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
//			   @Result(property="order_fee", column="order_fee",javaType=BigDecimal.class,jdbcType=JdbcType.DECIMAL),
//			   @Result(property="price", column="price",javaType=BigDecimal.class,jdbcType=JdbcType.DECIMAL),
//			   @Result(property="tt_num",column="tt_num",javaType=Integer.class,jdbcType=JdbcType.INTEGER)
//			 }) 
	//@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "order_id")  
	int insertOrder(Order order);
	
	@Update("UPDATE ysyy_order SET order_fee = #{order_fee} , price = #{price} WHERE order_id = #{order_id}")
	int updateOrder(@Param("order_id") int order_id,@Param("order_fee")BigDecimal order_fee,@Param("price")BigDecimal price);

	// 更新订单状态
	@Update("UPDATE ysyy_order SET order_status = #{order_status} WHERE order_id = #{order_id}")
	int updateOrderStatus(@Param("order_id")int order_id,@Param("order_status")short order_status);
	
	
	@Select("select `order_id`, o.`order_no`, `order_status`, o.`user_id`, o.`user_type`,`order_type`, o.`teacher_id`,o.`order_act`, "
			+ "`course_id`, `order_fee`, `price`, `tt_num` , u.user_account , tu.user_account teacher_account "
			+ "from `ysyy_order` o , `ysyy_user`  u ,  `ysyy_user` tu "
			+ "where `order_id` = #{order_id}  and u.user_id = o.user_id and tu.user_id = o.teacher_id ")
	OrderDetail getOrderDetail(@Param("order_id") int order_id);

	@Select("select order_fee as `order_price` from ysyy_order where order_id = #{order_id} limit 1")
	BigDecimal getOrderPrice(@Param("order_id") int order_id);
	
	// 获取学生的课时信息
	@Select("select u.`user_account`,oct.`tt_time`,oct.`start_time_id` "
            + "from `ysyy_order` o, `ysyy_order_ct` oct, `ysyy_user_teacher` t ,`ysyy_user` u "
           + "where o.`order_id` = oct.`order_id` and o.`teacher_id` = t.`teacher_id` and t.`user_id` = u.`user_id` "
           + "and o.`order_id` = ' . $order_id . ' and o.`user_id` = ' . $user_id . ' limit 1")
	CourseTime getCourseTime(@Param("user_id")int user_id,@Param("order_id") int order_id);
		
	// 获取活动模板编号
	@Select("select order_act from ysyy_order where order_id=#{order_id}")
	int getTemplateId(@Param("order_id") int order_id);

	@Select("SELECT * FROM `ysyy_order` WHERE `user_id`=#{user_id}")
	List<Order> getOrdersByUserId(@Param("user_id") int user_id);
}
