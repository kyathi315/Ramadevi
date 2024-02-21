package com.stocks.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stocks.DTO.UserDTO;
import com.stocks.Exceptions.UserException;
import com.stocks.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
	@Autowired
     UserService userService;

	 @PostMapping("/register")
	    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
	        try {
	            UserDTO registeredUser = userService.registerUser(userDTO);
	            return ResponseEntity.ok(registeredUser);
	        } catch (UserException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("The service is temporarily unavailable. Please try again later.");
	        }
	    }

	 @PostMapping("/login")
	    public ResponseEntity<?> loginUser( @RequestBody UserDTO userDTO) {
	        try {
	            boolean isLoggedIn = userService.loginUser(userDTO.getUsername(), userDTO.getPassword());
	            return ResponseEntity.ok("User logged in successfully");
	        } catch (UserException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("The login service is temporarily unavailable. Please try again later.");
	        }
	    }

}
