package com.kakao.pay.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
/*
@SpringBootApplication 어노테이션은 Spring의 성격을 갖고 있는 어노테이션들을 묶어 놓은 어노테이션입니다.
@Configuration(스프링 자바 기반 설정 클래스를 지정) 
& @EnableAutoConfiguration(root package 정의 및 스프링 설정 자동화) 
& @ComponentScan(컴포넌트 검색 기능 및 bean 자동 등)을 합쳐 놓은 어노테이션으로 
손쉽게 SpringBoot를 사용할 수 있습니다.*/

public class KakaopayApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakaopayApplication.class, args);
	}

}
