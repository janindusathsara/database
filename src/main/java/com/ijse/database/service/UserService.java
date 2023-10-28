package com.ijse.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.database.dto.UserPwdDto;
import com.ijse.database.entity.User;

@Service
public interface UserService {
    List<User> getAllUsers();
    User createUser(User user);
    User getUser(Long id);
    User changeUserPassword(Long id, UserPwdDto userPwdDto);
}
