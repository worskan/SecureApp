package com.pmh.secureapp;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "board")
public class Board {

	@Id
	@Column(name = "bno")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bno;
	private String content;
	private String title;
	private String username;

	public int getBno() {
		return bno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
