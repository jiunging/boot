package com.coding404.myweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.coding404.myweb.util.interceptor.UserAuthHandler;

@Configuration // 스프링 설정파일
public class WebConfig implements WebMvcConfigurer {

	// 1st
//	@Autowired
//	private UserAuthHandler userAuthHandler;
	
	// 인터셉터를 등록
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(userAuthHandler())
				.addPathPatterns("/product/**") // product로 들어오는 모든 경로를 검사?
				.addPathPatterns("/user/**") // user경로에 대해서 전부다.
				.excludePathPatterns("/user/login") // login은 패스에서 제외
				.excludePathPatterns("/user/loginForm") // 로그인 요청은 패스에서 제외
				.excludePathPatterns("/user/join"); // join은 패스에서 제외
		
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
	// 2nd
	@Bean
	public UserAuthHandler userAuthHandler() {
		return new UserAuthHandler();
	}
	

	
}
