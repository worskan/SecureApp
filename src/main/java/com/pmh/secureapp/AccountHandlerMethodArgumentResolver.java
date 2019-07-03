package com.pmh.secureapp;

import java.io.Reader;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.pmh.Domains.Account;

public class AccountHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return Account.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		Authentication auth = (Authentication) webRequest.getUserPrincipal();

		
		
		//유저 정보 가져오기
		// return user 정보  : if(auth != null && auth.getPrincipal() instanceof AccountPrincipal)
		//auth가 null이 아니고 유저 정보 타입이 AccountPrincipal이면 ((AccountPrincipal)auth.getPrincipal()).user 리턴 or null 리턴
		return auth != null && auth.getPrincipal() instanceof AccountPrincipal ? ((AccountPrincipal)auth.getPrincipal()).user : null;
	}

}
