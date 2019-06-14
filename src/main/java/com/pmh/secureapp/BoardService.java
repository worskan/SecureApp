package com.pmh.secureapp;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService{

	@Autowired
	private boardRepository Brepo;
	
	public void inputContent(Board board) {
		Brepo.save(board);
	}
	
	public List<Board> allContent() {
		
		return Brepo.findAll();
	}
}
