package com.pmh.Repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pmh.Domains.BoardComment;

@Transactional
public interface BoardCommentRepository extends JpaRepository<BoardComment, Integer> {
	public void deleteByCno(int cno); // 댓글 삭제
	List<BoardComment> findByBno(int bno); // List를 뽑을때는 public 쓰지않기
}