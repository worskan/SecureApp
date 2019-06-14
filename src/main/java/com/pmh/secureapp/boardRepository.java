package com.pmh.secureapp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface boardRepository extends JpaRepository<Board, Integer> {
	
}
