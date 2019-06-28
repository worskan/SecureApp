package com.pmh.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.pmh.Domains.Account;

@Service
public class SendMailService { //메일 보내는 서비스

	private JavaMailSender javaMailSender;

	@Autowired
	public SendMailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendMail(Account account) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(account.getEmail());		//메일을 전송할 유저의 이메일 아이디
		mail.setSubject("메일테스트");
		mail.setText(account.getUsername()+"님 회원가입한 것을 환영합니다.");
		
		javaMailSender.send(mail);
	}
}