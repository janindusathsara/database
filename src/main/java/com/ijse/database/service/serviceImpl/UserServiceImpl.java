package com.ijse.database.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.database.dto.UserPwdDto;
import com.ijse.database.entity.User;
import com.ijse.database.repository.UserRepository;
import com.ijse.database.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User changeUserPassword(Long id, UserPwdDto userPwdDto) {
        User userEntity = userRepository.findById(id).orElse(null);

        if (userEntity != null) {
            userEntity.setPassword(userPwdDto.getPassword());
            return userRepository.save(userEntity);
        } else {
            return null;
        }
    }

    
}
