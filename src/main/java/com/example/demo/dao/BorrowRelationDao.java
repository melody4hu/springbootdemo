package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.BorrowRelation;  

@Repository
@Transactional
public interface BorrowRelationDao extends JpaRepository<BorrowRelation, Long>{  
	  
    List<BorrowRelation> findByPersonid(Long personid);  
    
    BorrowRelation findByBookid(Long bookid);  
} 
