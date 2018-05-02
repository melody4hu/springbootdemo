package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BorrowRelation;  
  
public interface BorrowRelationDao extends JpaRepository<BorrowRelation, Long>{  
	  
    List<BorrowRelation> findByPersonid(Long personid);  
    
    BorrowRelation findByBookid(Long bookid);  
} 
