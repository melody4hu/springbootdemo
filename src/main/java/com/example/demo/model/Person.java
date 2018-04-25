package com.example.demo.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;  
  
@Entity
@Table(name="tb_person")
public class Person implements Serializable, UserDetails{  
  
	/**
	 * 
	 */
	private static final long serialVersionUID = -3603831583180357725L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
	
	@Column(name="username", length=45,nullable=false)
    private String username;
	
	@Column(length=45,nullable=false)
    private String name;  
     
	@Column(length=45,nullable=false)
    private String password;
	
    public Long getId() {  
        return id;  
    }
    
    public void setId(Long id) {
    	this.id = id;
    }
    
    public String getUsername() {  
        return username;  
    }  
  
    public void setUsername(String username) {  
        this.username = username;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}    
} 
