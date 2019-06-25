package com.pmh.Controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pmh.Domains.Account;
import com.pmh.Service.RegistService;
import com.pmh.secureapp.AccountPrincipal;

import groovy.transform.ToString;

@Controller
public class HomeController {

	@Autowired
	private RegistService service;

	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping("userList") //모든 사용자의 정보 리스트
	public void userList(Model model) {
		List<Account> userList = service.userList();
		model.addAttribute("userList", userList);
	}

	@RequestMapping("/login") //로그ㅡ인 페이지
	public String loginPage(AccountPrincipal Apcal) {

		return "login";
	}

	@RequestMapping("/logout") //로그아웃 페이지
	public void logoutPage() {
	}

	@RequestMapping("/logout-success") //로그인 성공 페이지
	public void logoutSuccess(String username) {
	}
	
	
	 //차후 로그인한 유저가 추가할수 있게끔 변경
	 //현재 누구나 연락처 추가 가능
	@GetMapping("showUser" ) //사용자의 상세정보
	public void showUser(String username, Model model) {
		Account account = service.findByUsername(username);
		model.addAttribute("username", account);
		System.out.println("유저 전화번호: " + account.phone);
	}

}
