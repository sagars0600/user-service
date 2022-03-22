package com.user.userservice.controller;


import com.user.userservice.model.User;
import com.user.userservice.repo.UserRepository;
import com.user.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    @PutMapping("/users/{userId}")
    public ResponseEntity<User> update(@Valid @RequestBody User user, @PathVariable("userId")  String userId) throws Exception {
        return new ResponseEntity(userService.update(user,userId),HttpStatus.ACCEPTED);
    }




    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){
        User user = userService.getuserById(userId);
        return new ResponseEntity(user, HttpStatus.CREATED);

    }




    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable("userId") String userId){
        return new ResponseEntity<>(userService.deleteUserById(userId), HttpStatus.ACCEPTED);
    }

}
