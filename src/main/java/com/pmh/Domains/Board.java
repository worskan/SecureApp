package com.pmh.Domains;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "board")
@SequenceGenerator(name = "board_SEQ_GENERATOR",sequenceName = "board_SEQ",initialValue = 1,allocationSize = 1)
public class Board {

	@Id
	@Column(name = "bno")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "board_SEQ_GENERATOR")
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
