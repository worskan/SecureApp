package com.pmh.secureapp;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhoneService{

	@Autowired
	private PhoneRepository Prepo;


	@Transactional
	public void getPhones(Phone phone) {
		Prepo.saveAndFlush(phone);
	}

	
	
}
