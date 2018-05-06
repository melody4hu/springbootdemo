package com.example.demo.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.BookDao;
import com.example.demo.dao.BorrowRelationDao;
import com.example.demo.dao.PersonDao;
import com.example.demo.model.Book;
import com.example.demo.model.BorrowRelation;

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
    public String bookrelation(@RequestParam("bookid") Long bookid, HttpServletRequest request) throws IOException {
		if (borrowRelationDao.findByBookid(bookid) == null) {
				
			Long userid =  personDao.findByUsername(request.getRemoteUser()).getId();
			
			borrowbookFunc(bookid, userid);

			return "available";
		}
		else {
			return "nonavailable";
		}
	}

	@Transactional(readOnly = true) 
	private void borrowbookFunc(Long bookid, Long userid) {
		BorrowRelation br = new BorrowRelation();
		br.setBookid(bookid);
		br.setPersonid(userid);
		borrowRelationDao.save(br);
		
		Book book = bookDao.findById(bookid).get();
		book.setBorrowed(1);
		bookDao.save(book);
	}
}
