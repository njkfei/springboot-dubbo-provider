package com.sanhao.tech.data.service.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import com.sanhao.tech.data.service.dao.BillDAO;
import com.sanhao.tech.data.service.dao.CouponDAO;
import com.sanhao.tech.data.service.dao.CourseDAO;
import com.sanhao.tech.data.service.dao.OrderCTDAO;
import com.sanhao.tech.data.service.dao.OrderDAO;
import com.sanhao.tech.data.service.dao.TeacherTimeDAO;
import com.sanhao.tech.data.service.dao.UserDAO;
import com.sanhao.tech.sevice.model.CouponTemplate;
import com.sanhao.tech.sevice.model.Course;
import com.sanhao.tech.sevice.model.CourseTime;
import com.sanhao.tech.sevice.model.Order;
import com.sanhao.tech.sevice.model.OrderCT;
import com.sanhao.tech.sevice.model.OrderCTDetail;
import com.sanhao.tech.sevice.model.OrderCTList;
import com.sanhao.tech.sevice.model.OrderDetail;
import com.sanhao.tech.sevice.model.Teacher;
import com.sanhao.tech.sevice.model.TeacherTime;
import com.sanhao.tech.sevice.service.OrderService;
import com.sanhao.tech.sevice.service.TeacherService;
import com.sanhao.tech.sevice.service.UserService;

public class OrderServiceImpl implements OrderService {
	private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);
	@Autowired
	OrderDAO orderDAO;
	@Autowired
	OrderCTDAO orderCTDAO;

	public Order getOrder(int order_id) {
		return orderDAO.getOrder(order_id);
	}

	public List<OrderCT> getOrderCTList(int order_id) {
		return orderCTDAO.getOrderCTList(order_id);
	}


	public int updateOrder(int order_id, BigDecimal order_fee, BigDecimal price) {
		return orderDAO.updateOrder(order_id, order_fee, price);
	}

	public int updateOrderCTFee(int oct_id, BigDecimal oct_fee) {
		return orderCTDAO.updateOrderCTFee(oct_id, oct_fee);
	}

	
	// 获取活动模板编号
	public int getTemplateId(int order_id){
		return orderDAO.getTemplateId(order_id);
		
	}

	public List<OrderCT> getOctKeyInfoList(int order_id) {
		return orderCTDAO.getOctKeyInfoList(order_id);
	}
}
