package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Book;  
  
public interface BookDao extends JpaRepository<Book, Long>{  
	  
    Book findByName(String name);  
} 
