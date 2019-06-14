package com.pmh.secureapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pmh.Domains.Account;
import com.pmh.Repositories.AccountRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private AccountRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account user = repo.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("User 404");

		return new AccountPrincipal(user);
	}

	public String regist(Account account, String username) {

		if (repo.findByUsername(username) != null) {

			return "registFail";
		} else {
			repo.save(account);
			return "registSuccess";
		}
	}

}
