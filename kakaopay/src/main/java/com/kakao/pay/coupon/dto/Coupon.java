package com.kakao.pay.coupon.dto;

import lombok.Data;

@Data
public class Coupon {
	private String id;
	private String couponCode;	
	private String email;
	private int useYN;
	private String regDate;
	private String payDate;
	private String expirationDate;

}

