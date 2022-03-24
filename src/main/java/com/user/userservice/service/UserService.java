package com.user.userservice.service;

import com.user.userservice.exception.UserNotFoundException;
import com.user.userservice.model.User;
import com.user.userservice.model.UserDto;
import com.user.userservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;
import java.util.Date;




import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;





    public User addUser(User user){
        return userRepository.save(user);
    }

    public String deleteUserById(String userId){
        userRepository.deleteById(userId);
        return "User Deleted Successfully";
    }

    public List<UserDto> allUser(Integer page,Integer pageSize) {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Pageable firstPage = (Pageable) PageRequest.of(page - 1, pageSize);
        List<User> allUsers = userRepository.findAll( firstPage).toList();
        List<UserDto> allUsersDTO = new ArrayList<>();
        for (User user : allUsers) {
            UserDto userDTO = new UserDto(user.getUserId(), user.getFirstName(), user.getMiddleName(),
                    user.getLastName(), user.getPhoneNumber(), user.getDateOfBirth(), user.getGender(),
                    user.getAddress(), user.getEmployeeNumber(), user.getBloodGroup(), user.getEmail());
            allUsersDTO.add(userDTO);
        }
        return allUsersDTO;
    }



        public User update(User user, String userId) throws Exception {
        if (userRepository.findById(userId).isPresent()) {
            return this.userRepository.save(user);
        } else {
            throw new Exception("ID doesnot Exist");
        }

    }


    public Optional<User> getuserById(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent())
            throw new UserNotFoundException("Please check the ID");
        else return user;
    }

    public User userEmail(String email){
        if(userRepository.findEmail(email)!=null){
            return userRepository.findEmail(email);
        }
        else{
            throw new UserNotFoundException("Email Exception not found");
        }
    }


}
