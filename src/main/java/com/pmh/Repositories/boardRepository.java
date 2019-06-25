package com.pmh.Repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pmh.Domains.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
	Board findByBno(int bno);
	List<Board> findAllByOrderByBno(); // List를 뽑을때는 public 쓰지않기
	
	/*페이지네이션 */
	Page<Board> findAll(Pageable pageable); 
	Page<Board> findAllByOrderByBnoDesc(Pageable pageable);
	Page<Board> findAllByUsername(String username,Pageable pageable);
}
