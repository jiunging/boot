package com.coding404.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration // 설정파일임
@EnableWebSecurity // 이 설정파일을 시큐리티 필터에 등록시킴
public class SecurityConfig {
	
	// 비밀번호 암호화 객체(시큐리티가 제공해줌)
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		// csrf라는 토큰이 있는데, 사용은 안 함(모르면 나중에 찾아보자)
		http.csrf().disable();
		
		// 권한설정
		// 모든 요청에 대해서 사용자 인증이 필요함(어느 경로로 가도 login페이지로 이동됨 지금은)
//		http.authorizeRequests( (authrize) -> authrize.anyRequest().authenticated() );
		
		// 특정페이지 인증(hello라는 페이지는 인증이 필요함)
//		http.authorizeRequests( (authrize) -> authrize.antMatchers("/hello").authenticated() );
		
		// user로 시작하는 모든 페이지
		// user/모든경로는 인증이 필요하다는 뜻
//		http.authorizeRequests( (authrize) -> authrize.antMatchers("/user/**").authenticated() ); 
		
		// 인증이 돼도, role이라는게 없으면 못 들어감
		// 유저페이지는 USER 권한이 필요함, 어드민페이지는 ADMIN 권한이 필요함 (인증이 되어도 따로 권한이라는게 필요)
//		http.authorizeRequests( (authorize) -> authorize.antMatchers("/user/**").hasRole("USER")
//														.antMatchers("/admin/**").hasRole("ADMIN") );
		
		// 총 뜻: all페이지는 인증만 있으면 됨, user랑 admin은 인증 및 권한이 필요함, 나머지 모든 요청은 허용
//		http.authorizeRequests( (authrize) -> authrize.antMatchers("/all").authenticated()
//													  .antMatchers("/user/**").hasRole("USER")
//													  .antMatchers("/admin/**").hasRole("ADMIN")
//													  .anyRequest().permitAll() );
		
//		뜻: all페이지는 인증만 있으면 됨 
//		user페이지는 user 또는 admin 또는 tester 얘네들은 다 접근 가능 
//		admin은 admin만 가능 
//		나머지는 허용
		http.authorizeRequests( (authorize) -> authorize.antMatchers("/all").authenticated()
														.antMatchers("/user/**").hasAnyRole("USER", "ADMIN", "TESTER")
														.antMatchers("/admin/**").hasRole("ADMIN")
														.anyRequest().permitAll());
		
		
		// 시큐리티 기반의 폼 로그인을 사용하겠다
		http.formLogin()
			.loginPage("/login") // 우리가 만들어 놓은 커스터마이징된 페이지의 경로를 로그인 페이지로 사용함
			.loginProcessingUrl("/loginForm") // 로그인을 시도하는 경로
//			.usernameParameter("xxx") // username 파라미터 변경 시 // loginform에서 username이라는 name값을 받아옴
//			.passwordParameter("yyy") // password 파라미터 변경 시
			.defaultSuccessUrl("/hello") // 로그인 성공 후 보낼 경로
			.failureUrl("/login?err=true")
			// 이거도 가능
//			.failureHandler(authenticationFailureHandler());
			.and()
			.exceptionHandling().accessDeniedPage("/deny") // 권한이 없을 시에 처리
			.and()
			.logout().logoutUrl("/myLogout").logoutSuccessUrl("/hello"); // 로그아웃 주소 /myLogout으로, 로그아웃 이후에는 /hello로
		

		
		return http.build();
		
	}
	
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		
		CustomAuthencationFailure custom = new CustomAuthencationFailure();
		custom.setRedirectURL("/login?err=true");
		
		return custom;
		
		
	}
	

}
