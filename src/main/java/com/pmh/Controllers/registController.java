package com.pmh.Controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pmh.Domains.Account;
import com.pmh.Service.RegistService;
import com.pmh.Service.SendMailService;

import groovy.transform.ToString;

@Controller
public class registController {

	@Autowired
	private RegistService service;

	@Autowired
	private SendMailService SMService;

	@RequestMapping("/regist") // 회원가입 페이지
	public void registPage() {
	}

	@PostMapping("/registRst") // 회원가입 처리                             
	public String registProcess(Account account,String username, String password, String email) throws Exception {
//		Account account = new Account();
		System.out.println(account.getUsername());
		System.out.println("아이디: " + username);
		System.out.println("비밀번호: " + password);

		if ((account = service.findByUsername(account.getUsername())) != null) { // 회원가입시 db에 동일한 아이디가 있을 경우
			System.out.println("동일 아이디로 가입 확인");
			return "registFail";
		} else { // 동일한 아이디가 없을 경우
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			Account account1 = new Account();
			account1.setPassword(passwordEncoder.encode(password));
			account1.setUsername(username);
			account1.setEmail(email);

//			SMService.sendMail(account1);

			service.regist(account1);
			return "registSuccess";
		}
	}

	@RequestMapping("/emailConfirm") // 이메일 인증
	public void emailConfirm(String username, Model model) throws Exception {
		service.cngEmail_status(username);
		
		model.addAttribute("username", username);
	}
}