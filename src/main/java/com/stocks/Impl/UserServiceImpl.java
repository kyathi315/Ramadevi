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

	 public UserDTO registerUser(UserDTO userDTO) {
	        if (userRepository.existsByUsername(userDTO.getUsername())) {
	            throw new UserException("User already exists with username: " + userDTO.getUsername());
	        }

	        if (userRepository.existsByEmail(userDTO.getEmail())) {
	            throw new UserException("User already exists with email: " + userDTO.getEmail());
	        }

	        if (userDTO.getPassword() == null || userDTO.getPassword().trim().isEmpty()) {
	            throw new UserException("Password is required");
	        }

	        String encodedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
	        User user = UserMapper.toEntity(userDTO);
	        user.setPassword(encodedPassword);

	        user = userRepository.save(user);
	        return UserMapper.toDTO(user);
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
