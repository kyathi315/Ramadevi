package com.stocks.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stocks.DTO.UserDTO;
import com.stocks.Exceptions.UserException;
import com.stocks.Mapper.UserMapper;
import com.stocks.Repository.UserRepository;
import com.stocks.service.UserService;
import com.stocks.user.User;

@Service
public class UserServiceImpl implements UserService{
	
	 @Autowired
	 UserRepository userRepository;


	 private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


	 @Override
	    public void saveUser(String email, String username, String password, byte[] image) {
	        try {
	            User user = new User();
	            user.setEmail(email);
	            user.setUsername(username);
	            user.setPassword(bCryptPasswordEncoder.encode(password)); 
	            user.setImage(image);

	            userRepository.save(user);
	        } catch (Exception e) {
	            System.out.println("Error while saving user: " + e.getMessage());
	            throw new RuntimeException("Error while saving user.", e);
	        }
	    }
	 @Override
	 public boolean loginUser(String username, String password) {
	     User user = userRepository.findByUsername(username);
	     if (user == null) {
	         throw new UserException("Invalid username.");
	     }
	     if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
	            throw new UserException("Incorrect password.");
	        }
	     return true;
	 }
	

}
