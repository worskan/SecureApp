package com.pmh.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pmh.Domains.Board;

@Repository
public interface boardRepository extends JpaRepository<Board, Integer> {
	
}
