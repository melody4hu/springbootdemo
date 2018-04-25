package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Role;  
  
public interface RoleDao extends JpaRepository<Role, Long>{  
	  
} 
