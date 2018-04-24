package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;  
  
@Entity
@Table(name="tb_borrow_relation")
public class BorrowRelation implements Serializable {  
  

	/**
	 * 
	 */
	private static final long serialVersionUID = 2899534001175046790L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
	
	@Column(nullable=false)
	private Long personid;
	
	@Column(nullable=false)
	private Long bookid; 
      
    public Long getId() {  
        return id;  
    }
    
    public void setId(Long id) {
    	this.id = id;
    }
    
    public Long getPersonid() {  
        return personid;  
    }
    
    public void setPersonid(Long personid) {
    	this.personid = personid;
    } 
  
    public Long getBookid() {  
        return bookid;  
    }
    
    public void setBookid(Long bookid) {
    	this.bookid = bookid;
    }   
} 
