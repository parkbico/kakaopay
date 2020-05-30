package com.kakao.pay.coupon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@GetMapping("/goindex")
	public String index() {
		return "index.html";
	}
}