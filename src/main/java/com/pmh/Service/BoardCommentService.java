package com.pmh.Service;

import java.util.Collection;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmh.Domains.BoardComment;
import com.pmh.Repositories.BoardCommentRepository;

@Service
public class BoardCommentService {

	@Autowired
	private BoardCommentRepository bcrepo;

	
	public List<BoardComment> findByBno(int bno) {
		return bcrepo.findByBno(bno);	
	}

	// 코멘트 갯수
	public void commentCunt() {
		bcrepo.count();
	}

	//코멘트 리스트
	public List<BoardComment> commentList() {
		return bcrepo.findAll();
	}
	
	//코멘트 삽입
	public void commentInsert(BoardComment boardComment) {
		 bcrepo.save(boardComment);
	}

	//수정
	public BoardComment findByCno(int cno) {
		return bcrepo.findByCno(cno);
	}
	
	//삭제
	public void Cdel(int cno) {
		bcrepo.deleteByCno(cno);
	}
}