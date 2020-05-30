package com.kakao.pay.coupon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kakao.pay.coupon.dto.Coupon;

@Mapper
public interface CouponMapper {

	void insertCoupon(List<Coupon> list) throws Exception;

	Coupon getRandomCoupon();

	boolean updateEmail(Coupon coupon) throws Exception;

	List<Coupon> show(String email);

	Coupon getCouponInfo(String code);
	
	void useCoupon(String code) throws Exception;

	void cancelCoupon(String code) throws Exception;

	List<Coupon> expire();


}
