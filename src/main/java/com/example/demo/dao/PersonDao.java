package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Person; 

@Repository
@Transactional(readOnly = true) 
public interface PersonDao extends JpaRepository<Person, Long>{  
	  
    Person findByUsername(String username);  
} 
