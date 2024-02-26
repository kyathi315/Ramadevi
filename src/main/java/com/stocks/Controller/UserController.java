package com.stocks.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stocks.DTO.UserDTO;
import com.stocks.Exceptions.UserException;
import com.stocks.service.UserService;

import ch.qos.logback.core.model.Model;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
	@Autowired
     UserService userService;


//	  @PostMapping("/register")
//	    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
//	        try {
//	            UserDTO registeredUser = userService.registerUser(userDTO);
//	            return ResponseEntity.ok(registeredUser);
//	        } catch (UserException e) {
//	            return ResponseEntity.badRequest().body(e.getMessage());
//	        } catch (Exception e) {
//	            return ResponseEntity.internalServerError().body("An unexpected error occurred");
//	        }
//	    }
	  @PostMapping("/register")
	    public ResponseEntity<String> handleUserPhotoUpload(
	            @RequestParam("email") String email,
	            @RequestParam("username") String username,
	            @RequestParam("password") String password,
	            @RequestParam("image") MultipartFile image,
	            Model model) {
	        try {
	            byte[] imageBytes = image.isEmpty() ? null : image.getBytes();
	            userService.saveUser(email, username, password, imageBytes);
	            return new ResponseEntity<>("User upload successful", HttpStatus.CREATED);
	        } catch (IOException e) {
	            return new ResponseEntity<>("Error uploading user image: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Unexpected error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
