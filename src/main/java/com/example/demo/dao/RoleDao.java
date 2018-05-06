package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Role;  

@Repository
@Transactional
public interface RoleDao extends JpaRepository<Role, Long>{  
	  
} 
