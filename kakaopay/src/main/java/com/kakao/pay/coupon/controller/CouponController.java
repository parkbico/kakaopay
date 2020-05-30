package com.kakao.pay.coupon.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kakao.pay.coupon.dto.Coupon;
import com.kakao.pay.coupon.dto.Param;
import com.kakao.pay.coupon.service.CouponService;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

	@Autowired
	CouponService couponService;

	//1. 랜덤한 코드의 쿠폰을 N개 생성하여 데이터베이스에 보관하는 API를 구현
	@PostMapping("/create")
//  public Result create( @PathVariable("num") int num ) throws Exception {
	public HashMap<String,Object> create(@RequestBody Param param ) {
		//request : N개
		//response : 결과
		int num = param.getData1();
		HashMap<String,Object> map = couponService.create(num);
        return map;
    }


	//2. 생성된 쿠폰중 하나를 사용자에게 지급하는 API 구현
	@GetMapping("/provide/{email}")
    public Coupon provide( @PathVariable("email") String email ) throws Exception {
		//request : email같은 고유 값 <--email 검증 필요
		//response : coupon 정보 (코드, 발급날짜 , 유효기간)
		Coupon coupon = couponService.provide(email);
        return coupon;
    }


	//3. 사용자에게 지급된 쿠폰을 조회하는 API 구현
	@GetMapping("/show/{email}")
	public List<Coupon> show( @PathVariable("email") String email ) throws Exception {
		//request : email같은 고유 값
		//response : coupon 정보 (코드, 발급날짜 , 유효기간)
		List<Coupon> list = couponService.show(email);
        return list;
    }

	//4. 지급된 쿠폰중 하나를 사용하는 API 구현 (쿠폰 재사용은 불가)
	@PutMapping("/use/{code}")
	public HashMap<String,Object> use( @PathVariable("code") String code ) throws Exception {
		//request : 쿠폰번호
		//response : 결과
		HashMap<String,Object> map = couponService.use(code);
        return map;
    }

	//5. 지급된 쿠폰중 하나를 사용 취소하는 API를 구현(취소된 쿠폰 재사용 가능)
	@PutMapping("/cancel/{code}")
	public HashMap<String,Object> cancel( @PathVariable("code") String code ) throws Exception {
		//request : 쿠폰번호
		//response : 결과
		HashMap<String,Object> map = couponService.cancel(code);
        return map;
    }

	//6. 발급된 쿠폰중 당일 만료된 전체 쿠폰 목록을 조회하는 API를 구현
	@GetMapping("/expire")
	public List<Coupon> expire( HttpServletRequest request ) throws Exception {
		//request : 없음
		//response : 쿠폰 목록
		List<Coupon> list = couponService.expire();
        return list;
    }

}
