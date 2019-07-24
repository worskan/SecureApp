package com.pmh.Controllers;

import java.io.Reader;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pmh.Domains.Account;
import com.pmh.Repositories.AccountRepository;
import com.pmh.Service.RegistService;
import com.pmh.secureapp.AccountPrincipal;

import groovy.transform.ToString;

@Controller
public class HomeController {

	@Autowired
	private RegistService service;

	@Autowired
	private AccountRepository arepo;

	@RequestMapping("/")
	public String home(Account user) {

		return "/home";
	}

	@RequestMapping("userList") // 모든 사용자의 정보 리스트
	public void userList(Model model, Account account) {
		List<Account> userList = service.userList();
		model.addAttribute("userList", userList);
	}

	@RequestMapping("/login") // 로그ㅡ인 페이지
	public String loginPage() {
		return "login"; 
	}

	@RequestMapping(value = "/logincheck", method = RequestMethod.POST)
	public boolean logincheck(String username, String password) {
		System.out.println("입력 :"+username);
		System.out.println("입력 :"+password);
		
		Account user = service.findByUsername(username);
		System.out.println("사용자이름 :"+user.getUsername());
		System.out.println("사용자 비밀번호 :"+user.getPassword());
		System.out.println("인증 상태 :" +user.getEmail_status());
		
		if(username.equals(user.getUsername())) {
			System.out.println("입장");
			return true;
		}
		return false;
	}
	@RequestMapping("login_fail")
	public void login_fail() {
		
	}

	@RequestMapping("/logout") // 로그아웃 페이지
	public void logoutPage() {
	}

	@RequestMapping("/logout-success") // 로그인 성공 페이지
	public void logoutSuccess(String username) {
	}

	// 차후 로그인한 유저가 추가할수 있게끔 변경
	// 현재 누구나 연락처 추가 가능
	@GetMapping("showUser") // 사용자의 상세정보
	public void showUser(String username, Model model) {
		Account account = service.findByUsername(username);
		model.addAttribute("username", account);
		System.out.println("유저 전화번호: " + account.phone);
	}
	
//	@RequestMapping("header")
//	public void header( @AuthenticationPrincipal AccountPrincipal APcal, Model model) {
//		System.out.println("header 값: "+APcal.getUsername());
//		model.addAttribute("UID", APcal.getUsername());
//		
//	}

}
