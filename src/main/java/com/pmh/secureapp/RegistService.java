package com.pmh.secureapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistService {

	@Autowired
	private AccountRepository Arepo;

	@Autowired
	private PhoneRepository Prepo;

	public void regist(Account account) {
		Arepo.save(account);
	}

	public Account findByUsername(String username) {
		return Arepo.findByUsername(username);
	}


	public List<Account> userList() {
		return Arepo.findAll();
	}

	public Account getDetail(String username) {
		return Arepo.findByUsername(username);
	}

}
