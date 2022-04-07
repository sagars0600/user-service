//package com.user.userservice.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.user.userservice.model.User;
//import com.user.userservice.model.UserDto;
//import com.user.userservice.service.UserService;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.springframework.data.mongodb.core.aggregation.BooleanOperators.And.and;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@AutoConfigureMockMvc
//@WebMvcTest(UserController.class)
//class UserControllerTest {
//
//    @MockBean
//    private UserService userService;
//
//
//
//    @Autowired
//    MockMvc mockMvc;
//
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    void allUser() throws Exception {
//        User users = createUserList();
//        Mockito.when(userService.addUser(users)).thenReturn(users);
//        mockMvc.perform(post("/users")
//                .content(asJsonString(users))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    private User createUserList() {
//        User u1 = new User();
//        u1.setUserId("123");
//        u1.setFirstName("Nii");
//        u1.setMiddleName("dss");
//        u1.setLastName("deww");
//        u1.setAddress("kerala");
//      // u1.setDateOfBirth(new Date("2022-04-21"));
//        u1.setGender("AB_");
//        u1.setBloodGroup("O");
//        u1.setEmail("nik@gmail.com");
//        u1.setEmployeeNumber("258");
//        u1.setPassword("2365");
//        u1.setPhoneNumber("7894561230");
//
//        return u1;
//    }
//
//
//@Test
//    public  void deleteTest() throws Exception {
//        Mockito.when(userService.deleteUserById("1")).thenReturn("Deleted ");
//    this.mockMvc.perform(MockMvcRequestBuilders
//                    .delete("/users/1")
//                    .contentType(MediaType.APPLICATION_JSON))
//            .andDo(print())
//            .andExpect(status().isAccepted());
//}
//
//@Test
//    public  void updateTest() throws  Exception{
//User user=userUpdate();
//    Mockito.when(userService.update(user,"1")).thenReturn(user);
//    this.mockMvc.perform(MockMvcRequestBuilders
//                    .put("/users/1")
//                    .contentType(MediaType.APPLICATION_JSON))
//            .andDo(print())
//            .andExpect(status().isAccepted());
//
//}
//
//    private User userUpdate() {
//      User u1=new User();
//
//        u1.setUserId("123");
//        u1.setFirstName("Nii");
//        u1.setMiddleName("dss");
//        u1.setLastName("deww");
//        u1.setAddress("kerala");
//        u1.setGender("M");
//        u1.setBloodGroup("O");
//        u1.setEmail("nik@gmail.com");
//        u1.setEmployeeNumber("258");
//        u1.setPassword("2365");
//        u1.setPhoneNumber("7894561230");
//        return u1;
//    }
//
//    @Test
//    void getByIds() throws Exception {
//UserDto userDto=getById();
//        Mockito.when(userService.findID("1")).thenReturn(userDto);
//        this.mockMvc.perform(MockMvcRequestBuilders
//                        .get("/users/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isAccepted());
//    }
//
//    public UserDto getById(){
//        UserDto u1= new UserDto();
//        u1.setUserId("123");
//        u1.setFirstName("Nii");
//        u1.setMiddleName("dss");
//        u1.setLastName("deww");
//        u1.setAddress("kerala");
//        u1.setGender("M");
//        u1.setBloodGroup("O");
//        u1.setEmail("nik@gmail.com");
//        u1.setEmployeeNumber("258");
//
//        u1.setPhoneNumber("7894561230");
//        return u1;
//    }
//
//@Test
//    void getAllUser() throws Exception{
//        List<User> list =getList();
//    List<UserDto> userDTOList=createListDTOUser();
//    Mockito.when(userService.allUser(null,null)).thenReturn(userDTOList);
//    mockMvc.perform(get("/users"))
//            .andDo(print())
//            .andExpect(status().isAccepted())
//            .andExpect(jsonPath("$", Matchers.hasSize(2)))
//            .andExpect(jsonPath("$[0].firstName", Matchers.is("Natsu")))
//            .andExpect(jsonPath("$[1].firstName", Matchers.is("fg")));
//}
//
//
//public  List<User> getList(){
//        List<User> users= new ArrayList<>();
//        User u1= new User();
//    u1.setUserId("123");
//    u1.setFirstName("Nii");
//    u1.setMiddleName("dss");
//    u1.setLastName("deww");
//    u1.setAddress("kerala");
//    u1.setGender("M");
//    u1.setBloodGroup("O");
//    u1.setEmail("nik@gmail.com");
//    u1.setEmployeeNumber("258");
//
//    u1.setPhoneNumber("7894561230");
//
//    User u2= new User();
//    u2.setUserId("1234");
//    u2.setFirstName("Kill");
//    u2.setMiddleName("bs");
//    u2.setLastName("ls");
//    u2.setAddress("Kenya");
//    u2.setGender("F");
//    u2.setBloodGroup("O");
//    u2.setDateOfBirth(new Date(2022-04-21));
//    u2.setEmail("lis@gmail.com");
//    u2.setEmployeeNumber("2258");
//    u2.setPhoneNumber("7894561330");
//
//    users.add(u2);
//    users.add(u1);
//
//
//    return  users;
//}
//
//
//    private static  List<UserDto> createListDTOUser(){
//
//        List<UserDto> users = new ArrayList<>();
//        UserDto user= new UserDto(null,"Natsu","Igneel",
//                "Doddy","9710532160",new Date(2022-04-21), "MALE",
//                "hilai","123",
//                "A_POS","qw@ex.com");
//        UserDto user1= new UserDto(null,"fg","Igneel",
//                "Kane","9710532160",new Date(2022-04-21), "MALE",
//                "Ahilai","123",
//                "A_POS","qw1@ex.com");
//        users.add(user);
//        users.add(user1);
//        return users;
//    }
//
//    @Test
//    void userByEmail() throws Exception {
//        User user = createOne();
//        Mockito.when(userService.userEmail("nikhil@gmail.com")).thenReturn(user);
//        mockMvc.perform(get("/users/getUserByEmail/nikhil@gmail.com"))
//                .andDo(print())
//                .andExpect(status().isAccepted())
//                .andExpect(jsonPath("$",Matchers.aMapWithSize(12)))
//                .andExpect(jsonPath("$.email",Matchers.is("nikhil@gmail.com")));
//    }
//
//public User createOne(){
//    User user = new User("1", "Arun", "Kumar",
//            "nik", "1023456789", "M",
//            "Madhurai",  new Date(2022-03-21),"123",
//            "A+", "nikhil@gmail.com", "789456");
//    return  user;
//}
//
//
//}