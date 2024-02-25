package com.stocks.Mapper;

import com.stocks.DTO.UserDTO;
import com.stocks.user.User;

public class UserMapper {

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
//        user.setImage(userDTO.getImage());
        return user;
    }

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
//        userDTO.setImage(user.getImage());
        return userDTO;
    }

}
