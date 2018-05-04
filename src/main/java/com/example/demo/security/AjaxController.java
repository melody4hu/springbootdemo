package com.example.demo.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.BookDao;
import com.example.demo.dao.BorrowRelationDao;
import com.example.demo.dao.PersonDao;

@Controller
public class AjaxController {
	
	@Autowired
    PersonDao personDao;
	
	@Autowired
    BookDao bookDao;
    
	@Autowired
	BorrowRelationDao borrowRelationDao;
	
	
	@RequestMapping(value="/bookrelationAjax", method=RequestMethod.GET)  
	@ResponseBody
    public String bookrelation(@RequestParam("bookid") Long bookid) throws IOException {
		if (borrowRelationDao.findByBookid(bookid) == null) {
			return "available";
		}
		else {
			return "nonavailable";
		}
	}
	
	
}
