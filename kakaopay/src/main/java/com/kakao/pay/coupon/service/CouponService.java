package com.kakao.pay.coupon.service;

import java.util.HashMap;
import java.util.List;

import com.kakao.pay.coupon.dto.Coupon;

public interface CouponService {

	HashMap<String,Object> create(int num);

	Coupon provide(String email);

	List<Coupon> show(String email);

	HashMap<String,Object> use(String code);

	HashMap<String,Object> cancel(String code);

	List<Coupon> expire();

}
