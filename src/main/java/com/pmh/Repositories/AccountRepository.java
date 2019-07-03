package com.pmh.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pmh.Domains.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	Account findByUsername(String username);
	List<Account> findAll(); // List를 뽑을때는 public 쓰지않기
	
	
	@Modifying
	@Transactional
	@Query("update Account a set a.email_status=1 where a.username=?1")
	public void cngEmail_status(String username);
}
