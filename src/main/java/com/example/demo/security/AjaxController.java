package com.example.demo.security;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
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
	private EntityManagerFactory emf;
	
	@Autowired
    PersonDao personDao;
	
	@Autowired
    BookDao bookDao;
    
	@Autowired
	BorrowRelationDao borrowRelationDao;
	
	
	@RequestMapping(value="/bookrelationAjax", method=RequestMethod.GET)  
	@ResponseBody
    public String bookrelation(@RequestParam("bookid") Long bookid, HttpServletRequest request) throws IOException {
		Long userid =  personDao.findByUsername(request.getRemoteUser()).getId();
		return borrowbookFunc(bookid, userid);
	}

	private int getbookBorrowed(Long bookid) {
		int result = 0;
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		Book book = entityManager.find(Book.class, bookid, LockModeType.PESSIMISTIC_READ);
		result = book.getBorrowed();
		entityManager.getTransaction().commit();
		entityManager.close();
		return result;
	}
	
	@Transactional
	private String borrowbookFunc(Long bookid, Long userid) {
//		Book book = bookDao.getBookByIdForUpdate(bookid);
		if (getbookBorrowed(bookid) == 0) {
			EntityManager entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();
			Book book = entityManager.find(Book.class, bookid, LockModeType.PESSIMISTIC_WRITE);
			book.setBorrowed(1);
//			bookDao.save(book);
			entityManager.getTransaction().commit();
			entityManager.close();
			
	        BorrowRelation br = new BorrowRelation();
			br.setBookid(bookid);
			br.setPersonid(userid);
			borrowRelationDao.save(br);
			return "available";
		}
		else {
			return "nonavailable";
		}
		
	}
}
