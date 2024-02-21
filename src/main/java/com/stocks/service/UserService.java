package com.stocks.service;

import com.stocks.DTO.UserDTO;

public interface UserService {
	  UserDTO registerUser(UserDTO userDTO);
	   boolean loginUser(String username, String password);

}
