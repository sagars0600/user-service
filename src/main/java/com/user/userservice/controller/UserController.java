package com.user.userservice.controller;


import com.user.userservice.model.User;
import com.user.userservice.model.UserDto;
import com.user.userservice.repo.UserRepository;
import com.user.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping()
    public ResponseEntity<List<UserDto>> getallUsers(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize){
        return  new ResponseEntity<>(userService.allUser(page,pageSize), HttpStatus.ACCEPTED);
    }


    @PutMapping("/{userId}")
    public ResponseEntity<User> update(@Valid @RequestBody User user, @PathVariable("userId")  String userId) throws Exception {
        return new ResponseEntity(userService.update(user,userId),HttpStatus.ACCEPTED);
    }



    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){
        User user = userService.getuserById(userId);
        return new ResponseEntity(user, HttpStatus.CREATED);

    }






    @PostMapping
    public ResponseEntity<User> getAllUsers(@RequestBody User user){
        User users=  userService.addUser(user);
        return  new ResponseEntity<>(users, HttpStatus.OK);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable("userId") String userId){
        return new ResponseEntity<>(userService.deleteUserById(userId), HttpStatus.ACCEPTED);
    }


}
