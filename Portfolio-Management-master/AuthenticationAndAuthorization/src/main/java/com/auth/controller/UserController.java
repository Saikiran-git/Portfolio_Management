package com.auth.controller;

import com.auth.model.ApiResponse;
import com.auth.model.User;
import com.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.auth.model.Constants.HEADER_STRING;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
	
	String header;
	String user;

	@Autowired
	UserService userService;

	@GetMapping("/validate")
	public Boolean validate() {
		return true;
	}
	
	@GetMapping("/users")
	public ApiResponse<List<User>> listUser(HttpServletRequest req){
		header = req.getHeader(HEADER_STRING);
		user = userService.getUser(header);
		System.out.println("\n\n"+user+ " is making the request\n\n");
		return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.",userService.findAll());
	}
	
	@GetMapping("/whoami")
	public String test(HttpServletRequest req) {
		 header = req.getHeader(HEADER_STRING);
		 user = userService.getUser(header);
		 
		 // do your stuff
		 System.out.println("\n\n"+user+ " is making the request\n\n");
		 // To retrieve the current user object, iterate userService and retrieve the user object by comparing the username
		 
		return user;
	}
}


	