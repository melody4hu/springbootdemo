package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BookDao;
import com.example.demo.dao.BorrowRelationDao;
import com.example.demo.dao.PersonDao;
import com.example.demo.model.Book;
import com.example.demo.model.BorrowRelation;

@Controller
public class LoginController {
	
	@Autowired
    PersonDao personDao;
	
	@Autowired
    BookDao bookDao;
    
	@Autowired
	BorrowRelationDao borrowRelationDao;
	
	
	//Spring boot Security user authentication
	@RequestMapping(value="/login", method=RequestMethod.GET)  
    public String login() {  
		return "login.html";  
    }  
	
	@RequestMapping(value="/booklist", method = RequestMethod.GET)  
    public ModelAndView booklist(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("loginname", personDao.findByUsername(request.getRemoteUser()).getName());
		
		List<Book> bookList = bookDao.findAll();
		mav.addObject("books", bookList);
		mav.setViewName("booklist.html");
		return mav;
    } 
	
	@RequestMapping("/loginerror")  
    public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "loginerror.html";  
    } 
	
	@RequestMapping(value="/returnbook", method=RequestMethod.POST)  
    public String returnbook(Long bookid) {
		borrowRelationDao.delete(borrowRelationDao.findByBookid(bookid));
		
		Book book = bookDao.findById(bookid).get();
		book.setBorrowed(0);
		bookDao.save(book);
		return "redirect:/";
	}
	
	@RequestMapping(value="/borrowthisbook", method=RequestMethod.POST)  
    public String borrowthisbook(Long bookid, HttpServletRequest request) {
		if (borrowRelationDao.findByBookid(bookid) == null) {
			Long userid =  personDao.findByUsername(request.getRemoteUser()).getId();
			
			BorrowRelation br = new BorrowRelation();
			br.setBookid(bookid);
			br.setPersonid(userid);
			borrowRelationDao.save(br);
			
			Book book = bookDao.findById(bookid).get();
			book.setBorrowed(1);
			bookDao.save(book);
		}
		return "redirect:/booklist";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)  
    public ModelAndView userHomeControllerGet(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		String loginAcountid = request.getRemoteUser();
		mav.addObject("loginname", personDao.findByUsername(loginAcountid).getName());
		
		Long personId = personDao.findByUsername(loginAcountid).getId();
		List<BorrowRelation> brlist = borrowRelationDao.findByPersonid(personId);
		List<Book> bookList = new ArrayList<Book>();
		for (BorrowRelation br : brlist) {
			bookList.add(bookDao.findById(br.getBookid()).get());
		}
		mav.addObject("userBooks", bookList);
		mav.setViewName("user.html");
		return mav;
    } 
}
