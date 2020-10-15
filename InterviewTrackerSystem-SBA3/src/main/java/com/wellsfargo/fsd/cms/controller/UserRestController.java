package com.wellsfargo.fsd.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.fsd.cms.entity.User;
import com.wellsfargo.fsd.cms.exception.UserException;
import com.wellsfargo.fsd.cms.service.UserService;

@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	public UserService userService;	
	
	@PostMapping()
	public ResponseEntity<User> addUser(@RequestBody User user) throws UserException {
		return new ResponseEntity<User>(userService.addUser(user), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> findAllUsers() throws UserException {
		return new ResponseEntity<List<User>>(userService.displayAllUsers(),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable("userId") int userId) throws UserException {
		userService.deleteUser(userId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
