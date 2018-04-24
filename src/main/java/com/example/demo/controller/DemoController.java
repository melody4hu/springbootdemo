package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BookDao;
import com.example.demo.dao.BorrowRelationDao;
import com.example.demo.dao.PersonDao;
import com.example.demo.model.Book;
import com.example.demo.model.BorrowRelation;
import com.example.demo.model.Person;

@RestController
public class DemoController {
	
	@Autowired  
    PersonDao personDao;
	
	@Autowired  
    BookDao bookDao;
    
	@Autowired  
	BorrowRelationDao borrowRelationDao;
	
	@RequestMapping(value="/person", method=RequestMethod.GET)  
    public String PersonControllerByName(String name) {
		List<Person> result = personDao.findAll();
		if(name != null) {
			result = personDao.findByUsername(name);
		}
		String resultStr = "";
		for (Person person : result) {
			resultStr = resultStr + "USERNAME: " + person.getUsername() + ";   NAME: " + person.getName() + ";   BORROWEDBOOKS: " + getBorrowedBooks(person.getId()) +"<br/>";
		}
		return resultStr;
    }
	
	private String getBorrowedBooks(Long personid) {
		String resultStr = "";
		List<BorrowRelation> brList = borrowRelationDao.findByPersonid(personid);
		for (BorrowRelation br : brList) {
			Book book = bookDao.findById(br.getBookid()).get();
			resultStr = resultStr + book.getName() + ", ";
		}
		return resultStr;
	}
	
	@RequestMapping(value="/book", method=RequestMethod.GET)  
    public String BookControllerByName(String name) {
		List<Book> result = bookDao.findAll();
		if(name != null) {
			result = bookDao.findByName(name);
		}
		String resultStr = "";
		for (Book book : result) {
			resultStr = resultStr + "NAME: " + book.getName() + ";    BORROWED: " + book.getBorrowed()+"<br/>";
		}
		return resultStr;
    }
	
	@GetMapping("/login")  
    public ModelAndView login(){  
        return new ModelAndView("login"); 
    } 
	
	@RequestMapping("/login")  
    public String login(@RequestParam String username, @RequestParam String password) {  
        List<Person> personList = personDao.findByUsername(username); 
        if (personList != null && !personList.isEmpty() &&  personList.get(0).getPassword().equals(password)) {  
        	return "Login Successfully!"; 
        } else {  
            return "Login Failed!";
        }  
    }  
	
	@RequestMapping(value="/", method=RequestMethod.GET)  
    public String HomeControllerGet() {  
        return "Hello Melody!";  
    } 
}
