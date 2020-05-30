package com.kakao.pay.coupon.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakao.pay.coupon.dto.Coupon;
import com.kakao.pay.coupon.lib.CouponLib;
import com.kakao.pay.coupon.lib.EmailLib;
import com.kakao.pay.coupon.mapper.CouponMapper;

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	private CouponMapper couponMapper;

	@Override
	@Transactional
	public HashMap<String,Object> create(int num){

		HashMap<String,Object> map = new HashMap<String,Object>();

		boolean createResult = false;

		List<Coupon> list = new ArrayList<Coupon>();

		for(int i = 0 ; i< num ; i++) {
			Coupon coupon = new Coupon();
			coupon.setCouponCode(CouponLib.getCode());
			coupon.setUseYN(0);

			list.add(coupon);
		}


		//list insert
		try {
			couponMapper.insertCoupon(list);
			createResult = true;
			map.put("result", createResult);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	@Override
	@Transactional
	public Coupon provide(String email) {
		Coupon coupon = new Coupon();
		boolean emailCheck = EmailLib.isEmailFormat(email);

		if(emailCheck) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			TimeZone jst = TimeZone.getTimeZone("Asia/Seoul");
			Calendar cal = Calendar.getInstance(jst);

			//오늘 날짜
			String payDate = sdf.format(cal.getTime());
			//만료 날짜
			cal.add(Calendar.MONTH, 1);
			String expirationDate = sdf.format(cal.getTime());
			
			coupon = couponMapper.getRandomCoupon();
			if(coupon!=null) {
				coupon.setEmail(email);
				coupon.setPayDate(payDate);
				coupon.setExpirationDate(expirationDate);
				
				String tempEn = EmailLib.encryptEmail(email, coupon.getCouponCode());
				
				coupon.setId(tempEn);
				
				try {
					couponMapper.updateEmail(coupon);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return coupon;
	}

	@Override
	@Transactional
	public List<Coupon> show(String email) {
		
		List<Coupon> list = new ArrayList<Coupon>();
		boolean emailCheck = EmailLib.isEmailFormat(email);

		if(emailCheck ) {
	
			try {
				list = couponMapper.show(email);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return list;
	}

	@Override
	@Transactional
	public HashMap<String,Object> use(String code){

		HashMap<String,Object> map = new HashMap<String,Object>();
		boolean useResult = false;
		Coupon coupon = new Coupon();
		try {
			coupon = couponMapper.getCouponInfo(code);

			//id를 파라미터의 쿠폰번호로 디크립트 해서 나온 값이 email과 같나요?
			if(code.equals(coupon.getCouponCode())) {
				
				String tempEmail = EmailLib.decryptEmail(coupon.getId(), code);
				String originalEmail = coupon.getEmail();
				
				if(tempEmail.equals(originalEmail)) {
					couponMapper.useCoupon(code);
					useResult = true;
					map.put("result", useResult);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;

	}

	@Override
	@Transactional
	public HashMap<String,Object> cancel(String code){

		HashMap<String,Object> map = new HashMap<String,Object>();
		boolean cancelResult = false;
		Coupon coupon = new Coupon();

		try {
			coupon = couponMapper.getCouponInfo(code);

			//id를 파라미터의 쿠폰번호로 디크립트 해서 나온 값이 email과 같나요?
			if(code.equals(coupon.getCouponCode())) {
				
				String tempEmail = EmailLib.decryptEmail(coupon.getId(), code);
				String originalEmail = coupon.getEmail();
				
				if(tempEmail.equals(originalEmail)) {
					couponMapper.cancelCoupon(code);
					cancelResult = true;
					map.put("result", cancelResult);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;

	}


	@Override
	@Transactional
	public List<Coupon> expire(){

		List<Coupon> list = new ArrayList<Coupon>();

		try {
			list = couponMapper.expire();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}
}
