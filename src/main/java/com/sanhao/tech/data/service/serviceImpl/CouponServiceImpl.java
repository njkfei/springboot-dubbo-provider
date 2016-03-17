package com.sanhao.tech.data.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sanhao.tech.data.service.dao.CouponDAO;
import com.sanhao.tech.sevice.model.Coupon;
import com.sanhao.tech.sevice.model.CouponBuyInfo;
import com.sanhao.tech.sevice.model.CouponTemplate;
import com.sanhao.tech.sevice.service.CouponService;

public class CouponServiceImpl implements CouponService {
	@Autowired
	private CouponDAO couponDAO;
	
	public Coupon getCoupon(int coupon_id) {
		// TODO Auto-generated method stub
		return couponDAO.getCoupon(coupon_id);
	}

	public CouponTemplate getCouponTemplate(int template_id) {
		// TODO Auto-generated method stub
		return couponDAO.getCouponTemplate(template_id);
	}
	
	public CouponBuyInfo getCouponBuyInfo(int coupon_id,int user_id)
	{
		return couponDAO.getCouponBuyInfo(coupon_id,user_id);
	}

	public List<CouponTemplate> getCouponTemplateListByModel(int template_model) {
		// TODO Auto-generated method stub
		return couponDAO.getCouponTemplateListByModel(template_model);
	}

	public Integer getCouponId(int order_id) {
		// TODO Auto-generated method stub
		return couponDAO.getCouponId(order_id);
	}

}
