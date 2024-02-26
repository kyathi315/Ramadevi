package com.stocks.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.stocks.DTO.UserDTO;

public interface UserService {
//	  UserDTO registerUser(UserDTO userDTO);
	void saveUser(String email, String username, String password, byte[] image);
	   boolean loginUser(String username, String password);
//	UserDTO registerUser(UserDTO userDTO, MultipartFile image) throws IOException;

}
