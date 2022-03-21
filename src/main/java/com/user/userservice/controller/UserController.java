package com.user.userservice.controller;


import com.user.userservice.model.User;
import com.user.userservice.repo.UserRepository;
import com.user.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
      List<User> list=  userService.getAllUser();
      return  new ResponseEntity<>(list, HttpStatus.OK);
    }





}
