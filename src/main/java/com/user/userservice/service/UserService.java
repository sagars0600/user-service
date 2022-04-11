package com.user.userservice.service;

import com.user.userservice.constfile.ConstFiles;
import com.user.userservice.exception.UserNotFoundException;
import com.user.userservice.model.User;
import com.user.userservice.model.UserDto;
import com.user.userservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDto addUser(User user) {
        User email = userRepository.findByemail(user.getEmail());
        if (email != null) {
            throw new UserNotFoundException(ConstFiles.emails);
        }
        userRepository.save(user);
        UserDto userDTO = new UserDto();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setMiddleName(user.getMiddleName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setAddress(user.getAddress());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setEmployeeNumber(user.getEmployeeNumber());
        userDTO.setBloodGroup(user.getBloodGroup());
        userDTO.setGender(user.getGender());
        return userDTO;
    }

    public String deleteUserById(String userId) {
        try {

            userRepository.deleteById(userId);
            return "User Deleted Successfully";
        } catch (Exception e) {
            throw new UserNotFoundException(ConstFiles.passCode);
        }

    }

    public UserDto findID(String userId) {
        try {
            User user = userRepository.findById(userId).get();
            UserDto userDTO = new UserDto();
            userDTO.setUserId(user.getUserId());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setMiddleName(user.getMiddleName());
            userDTO.setPhoneNumber(user.getPhoneNumber());
            userDTO.setEmail(user.getEmail());
            userDTO.setAddress(user.getAddress());
            userDTO.setDateOfBirth(user.getDateOfBirth());
            userDTO.setEmployeeNumber(user.getEmployeeNumber());
            userDTO.setBloodGroup(user.getBloodGroup());
            userDTO.setGender(user.getGender());
            return userDTO;
        } catch (Exception e) {
            throw new UserNotFoundException(ConstFiles.errorCode);
        }
    }

    public List<UserDto> allUser(Integer page, Integer pageSize) {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Pageable firstPage = (Pageable) PageRequest.of(page - 1, pageSize);
        List<User> allUsers = userRepository.findAll(firstPage).toList();
        List<UserDto> allUsersDTO = new ArrayList<>();
        for (User user : allUsers) {
            UserDto userDTO = new UserDto(user.getUserId(), user.getFirstName(), user.getMiddleName(),
                    user.getLastName(), user.getPhoneNumber(), user.getDateOfBirth(), user.getGender(),
                    user.getAddress(), user.getEmployeeNumber(), user.getBloodGroup(), user.getEmail());
            allUsersDTO.add(userDTO);
        }
        return allUsersDTO;
    }

    public UserDto update(User user, String userId) throws Exception {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.save(user);
                UserDto userDTO = new UserDto();
                userDTO.setUserId(user.getUserId());
                userDTO.setFirstName(user.getFirstName());
                userDTO.setLastName(user.getLastName());
                userDTO.setMiddleName(user.getMiddleName());
                userDTO.setPhoneNumber(user.getPhoneNumber());
                userDTO.setEmail(user.getEmail());
                userDTO.setAddress(user.getAddress());
                userDTO.setDateOfBirth(user.getDateOfBirth());
                userDTO.setEmployeeNumber(user.getEmployeeNumber());
                userDTO.setBloodGroup(user.getBloodGroup());
                userDTO.setGender(user.getGender());
                return userDTO;
        } else {
            throw new UserNotFoundException(ConstFiles.errorCode);
        }
    }

    public User userEmail(String email) {
        if (userRepository.findByemail(email) !=null) {
            return userRepository.findByemail(email);
        } else {
            throw new UserNotFoundException(ConstFiles.emailNot);}
    }
}
