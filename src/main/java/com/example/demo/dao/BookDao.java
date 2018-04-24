package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Book;  
  
public interface BookDao extends JpaRepository<Book, Long>{  
	  
    List<Book> findByName(String name);  
} 
