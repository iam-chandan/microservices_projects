package com.ecommerce.usersService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.usersService.dto.Users;
import com.ecommerce.usersService.entity.UsersEntity;
import com.ecommerce.usersService.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private  UserService userService;
	
	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody Users users) {
		String id = userService.createUser(users);
		return ResponseEntity.ok("User data saved with id : " + id);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UsersEntity> getUser(@PathVariable String userId){
		UsersEntity users = userService.getUserById(userId);
		return ResponseEntity.ok(users);
	}
}
