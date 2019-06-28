package com.pmh.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pmh.Domains.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	Account findByUsername(String username);
	List<Account> findAll(); // List를 뽑을때는 public 쓰지않기
	Account save(Account account);
	
}
