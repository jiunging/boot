package com.coding404.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coding404.demo.command.MemberVO;
import com.coding404.demo.member.MemberMapper;

@Service // 빈으로 등록되어 있으면, 스프링을 UserDetailsService타입을 찾아서, 사용자가 로그인 시 loadUserByUsername 실행시킴
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberMapper memberMapper;
	
	// loginProcessingUrl에 로그인 등록
	// 스프링이 UserDetailsService타입을 찾아서, 사용자가 로그인 시 loadUserByUsername 메서드 실행시킴
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("사용자가 로그인을 시도함");
		System.out.println("사용자가 입력한 아이디: " + username);
		
		// 로그인 시도 (비밀번호는 시큐리티가 알아서 비교 후에 처리)
		// 로그인을 해서 VO객체
		MemberVO vo = memberMapper.login(username);
		System.out.println(vo);
		
		// 회원정보가 있음 -> 비밀번호 비교를 하기 위해서 UserDetails 타입으로 리턴
		if(vo != null) {
			return new MyUserDetails(vo); // 스프링 시큐리티가 비밀번호 비교, 롤 권한, 롤 확인도 해서 로그인 처리
		}
		
		// 시큐리티에 설정한 형식대로, 권한까지 처리를 해준다.
		// 만약, 아이디가 없거나, 비밀번호가 틀리면 login?error 기본이동된다 (설정으로 변경 가능)
		// 시큐리티는 특별한 세션의 모형을 사용함
		// 시큐리티 세션(new Authentication(new MyUserDetails() ) ) 모형으로 저장시킴
		
		return null;
	}

}
