package com.example.demo.dao;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Book; 

@Repository
@Transactional
public interface BookDao extends JpaRepository<Book, Long>{  
	  
    Book findByName(String name);
    
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)  
    @Query(value = "select j from Book j where j.id = :id ")  
    public Book getBookByIdForUpdate(@Param("id") Long id);
    
} 
