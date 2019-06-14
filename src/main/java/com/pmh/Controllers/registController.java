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

import groovy.transform.ToString;

@Controller
public class registController {

	@Autowired
	private RegistService service;

	@RequestMapping("/regist")
	public void registPage() {
	}

	@PostMapping("/registRst")
	public String registProcess(Account account, String username, String password) {
		System.out.println("아이디: " + username);
		System.out.println("비밀번호: " + password);
		if ((account = service.findByUsername(account.getUsername())) != null) {
			return "registFail";
		} else {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			Account account1 = new Account();
			account1.setPassword(passwordEncoder.encode(password));
			account1.setUsername(username);
			service.regist(account1);
			return "registSuccess";
		}
	}
}