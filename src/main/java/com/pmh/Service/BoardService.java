package com.pmh.Service;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmh.Domains.Board;
import com.pmh.Repositories.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository Brepo;

	public void inputContent(Board board) {
		Brepo.save(board);
	}

	public List<Board> allContent() {

		return Brepo.findAllByOrderByBno(); //finAll + ByOrderyBy?? = 정렬
	}

	public Board findByBno(int bno) {
		return Brepo.findByBno(bno);
	}

	public List<Board> finAll() {
		return Brepo.findAll();
	}

	public void BDel(int bno) {
		Brepo.deleteById(bno);
	}

	public Page<Board> findAll(Pageable pageable){
		return Brepo.findAll(pageable);
		
	}
	

}