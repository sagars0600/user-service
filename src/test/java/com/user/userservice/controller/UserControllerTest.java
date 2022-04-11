package com.user.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.userservice.constfile.ConstFiles;
import com.user.userservice.enums.BloodGroup;
import com.user.userservice.enums.Gender;
import com.user.userservice.model.User;
import com.user.userservice.model.UserDto;
import com.user.userservice.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetUsers()throws Exception {
        List<UserDto> userDto = createUserList();

        Mockito.when(userService.allUser(1, 2)).thenReturn(userDto);

        mockMvc.perform(get("/users?page=1&pageSize=2"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("firstTest")));
    }



    private List<UserDto> createUserList() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date c= sdf.parse("2015-05-26");
        List<UserDto> users = new ArrayList<>();

        UserDto user1 = new UserDto();
        user1.setUserId("A123A");
        user1.setFirstName("firstTest");
        user1.setMiddleName("J");
        user1.setLastName("S");
        user1.setPhoneNumber("9090909090");
        user1.setEmail("prabhu@mail.com");
        user1.setAddress("chennai");
        user1.setDateOfBirth(c);
        user1.setEmployeeNumber("12345");
        user1.setBloodGroup(BloodGroup.O_POS);
        user1.setGender(Gender.MALE);

        UserDto user2 = new UserDto();
        user2.setUserId("22");
        user2.setFirstName("secondTest");
        user2.setMiddleName("J");
        user2.setLastName("S");
        user2.setPhoneNumber("9090909090");
        user2.setEmail("prabhu@mail.com");
        user1.setAddress("Madhurai");
        user1.setDateOfBirth(c);
        user2.setEmployeeNumber("12345");
        user2.setBloodGroup(BloodGroup.O_POS);
        user2.setGender(Gender.MALE);

        users.add(user1);
        users.add(user2);
        return users;
    }

    @Test
    public void testGetUserByID() throws Exception {
        UserDto userDto = createOneUser();

        Mockito.when(userService.findID("1")).thenReturn(userDto);

        mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.firstName", Matchers.is("FirstID")));
    }

    private UserDto createOneUser() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date c= sdf.parse("2015-05-26");
        UserDto user1 = new UserDto();

        user1.setUserId("1");
        user1.setFirstName("FirstID");
        user1.setMiddleName("J");
        user1.setLastName("S");
        user1.setPhoneNumber("9090909090");
        user1.setEmail("prabhu@mail.com");
        user1.setAddress("chennai");
        user1.setDateOfBirth(c);
        user1.setEmployeeNumber("12345");
        user1.setBloodGroup(BloodGroup.O_POS);
        user1.setGender(Gender.MALE);
        return user1;
    }

    @Test
    public void testGetUserByEmail() throws Exception {
        User userDto = email();

        Mockito.when(userService.userEmail("prabhu@mail.com")).thenReturn(userDto);

        mockMvc.perform(get("/users/getUserByEmail/prabhu@mail.com"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.firstName", Matchers.is("Prabhu")));
   }

    @Test
    public void testDeleteUser() throws Exception {

        Mockito.when(userService.deleteUserById("1")).thenReturn(ConstFiles.passCode);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = createOneUserToPost();

        UserDto userDto = createUserDto();

        Mockito.when(userService.addUser(user)).thenReturn(userDto);
        mockMvc.perform(post("/users")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private UserDto createUserDto() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date c= sdf.parse("2015-05-26");
        UserDto user = new UserDto();
        user.setUserId("1");
        user.setFirstName("Prabhu");
        user.setMiddleName("J");
        user.setLastName("S");
        user.setAddress("chennai");
        user.setDateOfBirth(c);
        user.setPhoneNumber("9090909090");
        user.setEmail("prabhu@mail.com");
        user.setEmployeeNumber("12345");
        user.setBloodGroup(BloodGroup.O_POS);
        user.setGender(Gender.MALE);
        return user;
    }

    private User email() {
        User user = new User();
        user.setUserId("1");
        user.setFirstName("Prabhu");
        user.setMiddleName("J");
        user.setLastName("S");
        user.setPhoneNumber("9090909090");
        user.setEmail("prabhu@mail.com");
        user.setEmployeeNumber("12345");
        user.setBloodGroup(BloodGroup.O_POS);
        user.setGender(Gender.MALE);
        user.setPassword("12345");

        return user;
    }

    private User createOneUserToPost() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date c= sdf.parse("2015-05-26");
        User user = new User();
        user.setUserId("1");
        user.setFirstName("Prabhu");
        user.setMiddleName("J");
        user.setLastName("S");
        user.setPhoneNumber("9090909090");
        user.setAddress("chennai");
        user.setDateOfBirth(c);
        user.setEmail("prabhu@mail.com");
        user.setEmployeeNumber("12345");
        user.setBloodGroup(BloodGroup.O_POS);
        user.setGender(Gender.MALE);
        user.setPassword("12345");
        return user;
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = createOneUserToPost();
        UserDto userDto = new UserDto();

        Mockito.when(userService.update(user, "1")).thenReturn(userDto);
        mockMvc.perform(put("/users/1")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

}
