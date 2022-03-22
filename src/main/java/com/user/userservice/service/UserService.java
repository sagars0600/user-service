package com.user.userservice.service;

import com.user.userservice.model.User;
import com.user.userservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String deleteUserById(String userId){
        userRepository.deleteById(userId);
        return "User Deleted Successfully";
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }


    public User getuserById(String userId) {
        User user = userRepository.findById(userId).get();
        return user;
    }


}
