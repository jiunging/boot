package com.coding404.myweb.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// @Component
public class UserAuthHandler implements HandlerInterceptor{

	// 로그인 여부를 확인하는 인터셉터
	// pre - 컨트롤러로 들어가기 전에 실행됨
	// post - 컨트롤러에서 dispatcherServlet으로 리턴 중에 실행됨
	// 스프링 설정파일에 인터셉터를 등록
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("controller 요청 전에 실행됨");
		
		// 로그인이 된 사람 확인
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		// null일 경우 error
		if(user_id != null) {
			return true; // 컨트롤러를 실행시킴
		} else {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false; // false - 컨트롤러가 실행되지 않음
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("controller 요청 후에 실행됨");
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	

}
