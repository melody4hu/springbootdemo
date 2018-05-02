package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.BookDao;
import com.example.demo.dao.BorrowRelationDao;
import com.example.demo.dao.PersonDao;
import com.example.demo.model.Book;
import com.example.demo.model.BorrowRelation;
import com.example.demo.model.Person;

@RestController
public class TestDemoController {
	
	@Autowired
    PersonDao personDao;
	
	@Autowired
    BookDao bookDao;
    
	@Autowired
	BorrowRelationDao borrowRelationDao;
	
	@RequestMapping(value="/person", method=RequestMethod.GET)  
    public String PersonControllerByName(String name) {
		if(name != null) {
			Person person = personDao.findByUsername(name);
			return "USERNAME: " + person.getUsername() + ";   NAME: " + person.getName() + ";   BORROWEDBOOKS: " + getBorrowedBooks(person.getId()) +"<br/>";
		} else {
			List<Person> allPerson = personDao.findAll();
			String resultStr = "";
			for (Person person : allPerson) {
				resultStr = resultStr + "USERNAME: " + person.getUsername() + ";   NAME: " + person.getName() + ";   BORROWEDBOOKS: " + getBorrowedBooks(person.getId()) +"<br/>";
			}
			return resultStr;
		}
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
		if (name != null) {
			Book book = bookDao.findByName(name);
			return "NAME: " + book.getName() + ";    BORROWED: " + book.getBorrowed()+"<br/>";
		} else {
			List<Book> allBooks = bookDao.findAll();
			String resultStr = "";
			for (Book book : allBooks) {
				resultStr = resultStr + "NAME: " + book.getName() + ";    BORROWED: " + book.getBorrowed()+"<br/>";
			}
			return resultStr;
		}
    }
	
	//Custom user authentication
//	@GetMapping("/login")  
//    public ModelAndView login(){  
//        return new ModelAndView("login"); 
//    } 
//	
//	@RequestMapping("/login")  
//    public ModelAndView login(@RequestParam String username, @RequestParam String password) {  
//		Person person = personDao.findByUsername(username); 
//        if (person != null && person.getPassword().equals(password)) {  
//        	return new ModelAndView("booklist");
//        } else {  
//        	return new ModelAndView("loginerror");
//        }  
//    }  
	
}
