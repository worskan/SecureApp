package com.pmh.Domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import groovy.transform.ToString;

@Entity
@Table(name = "phone")
@SequenceGenerator(name = "phone_SEQ_GENERATOR",sequenceName = "phone_SEQ",initialValue = 1,allocationSize = 1)
public class Phone {


	@Id
	@Column(name = "pnum")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "phone_SEQ_GENERATOR")
	private int pnum;

	@Column(name = "username")
	private String username;

	@Column(name = "otherphone")
	private String otherPhone;


	@Override
	public String toString() {
		return  otherPhone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public String getOtherPhone() {
		return otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}

}
