package com.user.userservice.service;

import com.user.userservice.model.User;
import com.user.userservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUser() {
        return userRepository.findAll();
    }


    public User update(User user, String userId) throws Exception {
        if (userRepository.findById(userId).isPresent()) {
            return this.userRepository.save(user);
        } else {
            throw new Exception("ID doesnot Exist");
        }

    }

}
