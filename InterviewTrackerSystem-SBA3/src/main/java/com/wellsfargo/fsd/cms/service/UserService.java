package com.wellsfargo.fsd.cms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.cms.dao.AttendeesRepository;
import com.wellsfargo.fsd.cms.dao.UserRepository;
import com.wellsfargo.fsd.cms.entity.User;
import com.wellsfargo.fsd.cms.exception.UserException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AttendeesRepository attendeesRepo;
	
	@Transactional
	public User addUser(User user) throws UserException {
		
		if (user != null) {
			if (userRepo.existsById(user.getUserId())) {
				throw new UserException("User ID already in use");
			}
			if (userRepo.existsByEmail(user.getEmail())) {
				throw new UserException("Email address already in use");
			}
			
			if (userRepo.existsByMobile(user.getMobile())) {
				throw new UserException("Mobile number already registered"); 
			}
			 
			userRepo.save(user);
		}
		return user;		
	}
	
	@Transactional
	public boolean deleteUser(int userId) throws UserException {
		
		if(!(userRepo.existsById(userId))) {
			throw new UserException("User ID not found");
		}
		
		userRepo.deleteById(userId);
		
		List<Integer> currentUserlist = attendeesRepo.findByUserId(Integer.toString(userId));
		if(!(currentUserlist.isEmpty())) {
			for(Integer i: currentUserlist) {
				attendeesRepo.deleteById(i);
			}
		}
		return true;
	}
	
	@Transactional
	public List<User> displayAllUsers() throws UserException {		
		
		return userRepo.findAllUsers();	
		
	}

}
