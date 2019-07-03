package com.pmh.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.mail.SimpleMailMessage;
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

	private JavaMailSender javaMailSender;
	
	@Autowired
	public RegistService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void regist(Account account) throws Exception { //회원가입 및 이메일 인증
//		Arepo.save(account);
		
		System.out.println("유저 이메일"+account.getEmail());
		Account account1  = new Account();
		String key = new TempKey().getKey(50, false); //인증키 생성
		
		account1.setUsername(account.getUsername());
		account1.setPassword(account.getPassword());
		account1.setEmail(account.getEmail());
		account1.setEmail_authCode(key);
		createAuthKey(account1); //인증키 db에 저장
		
		SimpleMailMessage mail = new SimpleMailMessage();
//		MailHandler sendmail = new MailHandler(mailSender);
		mail.setSubject("메일 인증 서비스");
		mail.setText(new StringBuffer().append("<h1>메일인증</h1>").append("<a href='http://localhost:8080/emailConfirm?email=").append(account.getEmail())
				.append("&username=").append(account.getUsername())
				.append("&key=").append(key)
				.append("'target='_blenk'>이메일 인증 확인</a>").toString());
		mail.setTo(account.getEmail());
		javaMailSender.send(mail);
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

	public void createAuthKey(Account account) { // 이메일 인증 랜덤키 생성
		account.getEmail();
		account.getEmail_authCode();
		Arepo.save(account);
	}

	public Account saveEmail(Account account) { // 인증된 이메일 저장
		return Arepo.save(account);
	}

	public void cngEmail_status(String username) {
		 Arepo.cngEmail_status(username);
	}
}
