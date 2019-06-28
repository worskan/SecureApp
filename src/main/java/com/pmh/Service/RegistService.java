package com.pmh.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.pmh.Domains.Account;
import com.pmh.Repositories.AccountRepository;
import com.pmh.Repositories.PhoneRepository;
import com.pmh.secureapp.MailHandler;
import com.pmh.secureapp.TempKey;

@Transactional
@Service
public class RegistService {

	@Autowired
	private AccountRepository Arepo;

	@Autowired
	private PhoneRepository Prepo;


	public void regist(Account account) throws Exception { //회원가입 및 이메일 인증
		Arepo.save(account);
	
	}

	public Account findByUsername(String username) { //사용자 찾기
		return Arepo.findByUsername(username);
	}

	public List<Account> userList() { //유저 리스트
		return Arepo.findAll();
	}

	public Account getDetail(String username) { //유저 상세항목
		return Arepo.findByUsername(username);
	}

}
