package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.BookDao;
import com.example.demo.dao.BorrowRelationDao;
import com.example.demo.dao.PersonDao;

@Controller
public class LoginController {
	
	@Autowired
    PersonDao personDao;
	
	@Autowired
    BookDao bookDao;
    
	@Autowired
	BorrowRelationDao borrowRelationDao;
	
	
	//Spring boot Security user authentication
	@RequestMapping("/login.html")  
    public String login() {  
		return "login.html";  
    }  
	
	@RequestMapping("/login-error.html")  
    public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login.html";  
    } 
}