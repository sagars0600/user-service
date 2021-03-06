package com.user.userservice.controller;


import com.user.userservice.model.User;
import com.user.userservice.model.UserDto;
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

@CrossOrigin(value = "*")
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
    public ResponseEntity<UserDto> update(@Valid @RequestBody User user, @PathVariable("userId")  String userId) throws Exception {
        return new ResponseEntity(userService.update(user,userId),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findID(@PathVariable("userId") String userId){
        return new ResponseEntity<>(userService.findID(userId),HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<UserDto> getAllUsers( @Valid @RequestBody User user){
        UserDto users=  userService.addUser(user);
        return  new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable("userId") String userId){
        return new ResponseEntity<>(userService.deleteUserById(userId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getUserByEmail/{emailId}")
    public ResponseEntity<User> userByEmail(@PathVariable("emailId") String emailId){
        return new ResponseEntity<>(userService.userEmail(emailId),HttpStatus.ACCEPTED);
    }

}
