package com.kakao.pay.coupon.lib;

import java.util.Random;

public class CouponLib {
	
	private final static int n = 16; // n자리 쿠폰 
	private final static char[] chs = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		
	
	public static String getCode() {
		Random rd = new Random();
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < n; j++) {
			char ch = chs[rd.nextInt(chs.length)];
			sb.append(ch);
		}
		return sb.toString();
	}
	

}
