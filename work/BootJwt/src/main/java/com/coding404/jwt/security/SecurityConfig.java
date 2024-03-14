package com.coding404.jwt.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.coding404.jwt.filter.FilterOne;
import com.coding404.jwt.filter.FilterTwo;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		// 1. 기본 설정
		http.csrf().disable();
		http.formLogin().disable(); // 폼로그인 동작 x
		http.httpBasic().disable(); // http의 기본 인증형태도 폐기 Authorization에는 원래 (아이디, 비밀번호) 형태인데, 이것을 폐기
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션기반의 인증을 사용하지 않고 추후 토큰방식을 사용함
		
		// 2. 크로스 오리진 필터 활성화
		http.cors( Customizer.withDefaults() );
		
		// 3. 시큐리티에 필터 추가
		// http.addFilter( new FilterOne() );
		// 시큐리티 타입의 필터가 아니라, 일반 필터라면, 시큐리티의 필터 전후로 필터를 추가
		
		// 사용자 정의 필터를 추가하고 싶으면, 이렇게 하면 됨!
		http.addFilterBefore(new FilterOne(), UsernamePasswordAuthenticationFilter.class); // 시큐리티 타입의 필터보다 이전에 커스터마이징한 필터를 등록
		http.addFilterAfter(new FilterTwo(), FilterOne.class);
		
		// 스프링 시큐리티 타입의 필터를 커스터마이징해서 사용(로그인)
		
		
		
		return http.build();
	}
	
	// 크로스 오리진 필터 - 서버가 다를 때, 옵션 설정을 보내게 되는데, 옵션 요청에 요청을 허용할 도메인 주소를 헤더에 담아 보내는 작업
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("https://example.com"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}

}
