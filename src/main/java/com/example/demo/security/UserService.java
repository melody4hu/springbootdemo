package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;

public class UserService implements UserDetailsService {

	@Autowired
	PersonDao personDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person person = personDao.findByUsername(username);
		if (person == null) {
			throw new UsernameNotFoundException("未查询到用户：" + username + "信息！");
		}
		return person;
	}
}
