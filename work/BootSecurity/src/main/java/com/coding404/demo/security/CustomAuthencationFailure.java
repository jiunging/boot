package com.coding404.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class CustomAuthencationFailure extends SimpleUrlAuthenticationFailureHandler {

	private String redirectURL;
	
	// 인증 실패 시 실행
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		// 요기에서는 로그인 실패 시에 다양한 어떤 처리작업을 사용하고
		response.sendRedirect(redirectURL);
		
	}

	// getter
	public String getRedirectURL() {
		return redirectURL;
	}

	// setter
	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}
	
	

	
	
	
	
}
