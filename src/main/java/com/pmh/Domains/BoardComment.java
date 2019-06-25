package com.pmh.Domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "boardcomment")
@SequenceGenerator(name = "comment_SEQ_GENERATOR",sequenceName = "comment_SEQ",initialValue = 1,allocationSize = 1)
public class BoardComment {

	@Id
	@Column(name = "cno")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "comment_SEQ_GENERATOR")
	private int cno;
	
	@Column(name = "bno")
	private int bno;
	
	private String bccomment;
	private String bcwriter;

	
	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}


	public String getBccomment() {
		return bccomment;
	}

	public void setBccomment(String bccomment) {
		this.bccomment = bccomment;
	}

	public String getBcwriter() {
		return bcwriter;
	}

	public void setBcwriter(String bcwriter) {
		this.bcwriter = bcwriter;
	}

}
