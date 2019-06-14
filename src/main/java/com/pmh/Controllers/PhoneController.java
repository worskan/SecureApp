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
import com.pmh.Domains.Phone;
import com.pmh.Repositories.AccountRepository;
import com.pmh.Service.PhoneService;

import groovy.transform.ToString;

@Controller
public class PhoneController {

	@Autowired
	private AccountRepository aservice;

	@Autowired
	private PhoneService pservice;



	@RequestMapping("/accessPhone")
	public void accessPhone(String username, Model model) {
		model.addAttribute("username", username);

	}

	@PostMapping("/addPhone")
	public String addPhone(String username, String other_phone) {
		Phone phone = new Phone();
		Account account = aservice.findByUsername(username);
		phone.setOtherPhone(other_phone);
		phone.setUsername(username);
		System.out.println(username);
		System.out.println(other_phone);
		pservice.getPhones(phone);

		return "addPhone";
	}

}
