package com.pmh.secureapp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	Account findByUsername(String username);
	List<Account> findAll();
	
	
}
