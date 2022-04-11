package com.user.userservice.service;


import com.user.userservice.constfile.ConstFiles;
import com.user.userservice.enums.BloodGroup;
import com.user.userservice.enums.Gender;
import com.user.userservice.exception.UserNotFoundException;
import com.user.userservice.model.User;
import com.user.userservice.model.UserDto;
import com.user.userservice.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {
    @InjectMocks
    UserService service;

    @Mock
    UserRepository userRepo;

    @Test
    void testCreateUser() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date c= sdf.parse("2015-05-26");
        UserDto userWithOutPassword = createOneUserToResponse();
        User userRequest = createOneUserRequestToPost();
        User user = new User();
        user.setFirstName("firstTest");
        user.setMiddleName("J");
        user.setLastName("S");
        user.setPhoneNumber("9090909090");
        user.setEmail("prabhu@mail.com");
        user.setDateOfBirth(c);
        user.setEmployeeNumber("12345");
        user.setBloodGroup(BloodGroup.O_POS);
        user.setGender(Gender.MALE);
        user.setPassword("1234");

        when(userRepo.save(any(User.class))).thenReturn(user);
        User savedUser = userRepo.save(user);
        when(service.addUser(userRequest)).thenReturn(userWithOutPassword);
        assertThat(savedUser.getFirstName()).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo("prabhu@mail.com");
    }

    @Test
    void testExceptionThrownWhenEmailAlreadyRegistered() throws ParseException {
        User user = createOneUserToCheck();
        User user1 = createOneUserToUpdate();
        User userRequest = new User();
        User ofResult =(user1);
        when(this.userRepo.save((User) any())).thenReturn(user);
        when(this.userRepo.findByemail((String) any())).thenReturn(ofResult);
        assertThrows(UserNotFoundException.class, () -> this.service.addUser(userRequest));
        verify(this.userRepo).findByemail((String) any());
    }

    @Test
    void testDeleteUserById() {
        service.deleteUserById("1");
        verify(userRepo, times(1)).deleteById("1");
    }

    @Test
    void testExceptionThrownWhenUserNotFound() {
        Mockito.doThrow(UserNotFoundException.class).when(userRepo).deleteById(any());
        Exception userNotFoundException = assertThrows(UserNotFoundException.class, () -> service.deleteUserById("1"));
        assertTrue(userNotFoundException.getMessage().contains(ConstFiles.passCode));
    }

    @Test
    void testExceptionThrownWhenUserIdNotFound() throws Exception {
        User user1 = createOneUserToUpdate();
        UserDto UserWithOutPassword = createOneUserToResponse();
        User userRequest = createOneUserRequestToPost();
        Mockito.when(userRepo.findById("1")).thenReturn(Optional.ofNullable(user1));
        assertThat(service.update(userRequest, "1")).isEqualTo(UserWithOutPassword);
        assertThrows(UserNotFoundException.class, () -> service.update(userRequest, null));
    }

    @Test
    void testGetUserById() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date c= sdf.parse("2015-05-26");
        User user = new User();
        user.setUserId("1");
        user.setFirstName("firstTest");
        user.setMiddleName("J");
        user.setLastName("S");
        user.setPhoneNumber("9090909090");
        user.setEmail("prabhu@mail.com");
        user.setDateOfBirth(c);
        user.setEmployeeNumber("12345");
        user.setBloodGroup(BloodGroup.O_POS);
        user.setGender(Gender.MALE);
        user.setPassword("1234");

        UserDto UserWithOutPassword = createOneUserToResponse();
        Mockito.when(userRepo.findById("1")).thenReturn(Optional.ofNullable(user));
        assertThat(service.findID(user.getUserId())).isEqualTo(UserWithOutPassword);
        assertThrows(UserNotFoundException.class, () -> service.findID(null));
    }

    @Test
    void testGetUserDetailsByEmail() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date c= sdf.parse("2015-05-26");
        User user = new User();
        user.setUserId("1");
        user.setFirstName("firstTest");
        user.setMiddleName("J");
        user.setLastName("S");
        user.setPhoneNumber("9090909090");
        user.setEmail("prabhu@mail.com");
        user.setDateOfBirth(c);
        user.setEmployeeNumber("12345");
        user.setBloodGroup(BloodGroup.O_POS);
        user.setGender(Gender.MALE);
        user.setPassword("1234");

        UserDto userDto = createOneUserToResponse();
        Mockito.when(userRepo.findByemail("prabhu@mail.com")).thenReturn((user));
        assertThat(service.userEmail(user.getEmail())).isEqualTo(user);
        assertThrows(UserNotFoundException.class, () -> service.userEmail(null));
    }

    @Test
    void testGetAllUsers() throws ParseException {
        User user = createOneUserToCheck();
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        PageImpl<User> pageImpl = new PageImpl<>(users);

        when(this.userRepo.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
        assertEquals(1, this.service.allUser(1, 3).size());
        verify(this.userRepo).findAll((org.springframework.data.domain.Pageable) any());
    }

    private UserDto createOneUserToResponse() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date c= sdf.parse("2015-05-26");
       UserDto userWithOutPassword = new UserDto();
        userWithOutPassword.setUserId("1");
        userWithOutPassword.setFirstName("firstTest");
        userWithOutPassword.setMiddleName("J");
        userWithOutPassword.setLastName("S");
        userWithOutPassword.setPhoneNumber("9090909090");
        userWithOutPassword.setEmail("prabhu@mail.com");
        userWithOutPassword.setDateOfBirth(c);
        userWithOutPassword.setEmployeeNumber("12345");
        userWithOutPassword.setBloodGroup(BloodGroup.O_POS);
        userWithOutPassword.setGender(Gender.MALE);
        return userWithOutPassword;
    }

    private User createOneUserRequestToPost() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date c= sdf.parse("2015-05-26");
        User userRequest = new User();
        userRequest.setUserId("1");
        userRequest.setFirstName("firstTest");
        userRequest.setMiddleName("J");
        userRequest.setLastName("S");
        userRequest.setPhoneNumber("9090909090");
        userRequest.setEmail("prabhu@mail.com");
        userRequest.setDateOfBirth(c);
        userRequest.setEmployeeNumber("12345");
        userRequest.setBloodGroup(BloodGroup.O_POS);
        userRequest.setGender(Gender.MALE);
        userRequest.setPassword("12345");
        return userRequest;
    }


    private User createOneUserToUpdate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date c= sdf.parse("2015-05-26");
        User user = new User();
        user.setUserId("1");
        user.setFirstName("firstTest");
        user.setMiddleName("J");
        user.setLastName("S");
        user.setPhoneNumber("9090909090");
        user.setEmail("prabhu@mail.com");
        user.setDateOfBirth(c);
        user.setEmployeeNumber("12345");
        user.setBloodGroup(BloodGroup.O_POS);
        user.setGender(Gender.MALE);
        user.setPassword("1234");

        return user;
    }

    private User createOneUserToCheck() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date c= sdf.parse("2015-05-26");
        User user = new User();
        user.setUserId("1");
        user.setFirstName("firstTest");
        user.setMiddleName("J");
        user.setLastName("S");
        user.setPhoneNumber("9090909090");
        user.setEmail("prabhu@mail.com");
        user.setDateOfBirth(c);
        user.setEmployeeNumber("12345");
        user.setBloodGroup(BloodGroup.O_POS);
        user.setGender(Gender.MALE);
        user.setPassword("1234");

        return user;
    }


    private List<User> createUsersList1() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date c= sdf.parse("2015-05-26");
        List<User> users = new ArrayList<>();
        User UserWithOutPassword1 = new User();
        UserWithOutPassword1.setUserId("1");
        UserWithOutPassword1.setFirstName("firstTest");
        UserWithOutPassword1.setMiddleName("J");
        UserWithOutPassword1.setLastName("S");
        UserWithOutPassword1.setPhoneNumber("9090909090");
        UserWithOutPassword1.setEmail("prabhu@mail.com");
        UserWithOutPassword1.setDateOfBirth(c);
        UserWithOutPassword1.setEmployeeNumber("12345");
        UserWithOutPassword1.setBloodGroup(BloodGroup.O_POS);
        UserWithOutPassword1.setGender(Gender.MALE);

        User UserWithOutPassword2 = new User();
        UserWithOutPassword2.setUserId("2");
        UserWithOutPassword2.setFirstName("secondTest");
        UserWithOutPassword2.setMiddleName("J");
        UserWithOutPassword2.setLastName("S");
        UserWithOutPassword2.setPhoneNumber("9090909090");
        UserWithOutPassword2.setEmail("prabhu@mail.com");
        UserWithOutPassword2.setDateOfBirth(c);
        UserWithOutPassword2.setEmployeeNumber("12345");
        UserWithOutPassword2.setBloodGroup(BloodGroup.O_POS);
        UserWithOutPassword2.setGender(Gender.MALE);

        users.add(UserWithOutPassword1);
        users.add(UserWithOutPassword2);
        return users;
    }

    private List<UserDto> createUsersList() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date c= sdf.parse("2015-05-26");
        List<UserDto> userWithOutPasswordList = new ArrayList<>();
      UserDto userWithOutPassword = new UserDto();
        userWithOutPassword.setUserId("623e238c0f3e41623a004497");
        userWithOutPassword.setFirstName("Vishnu");
        userWithOutPassword.setMiddleName("J");
        userWithOutPassword.setLastName("S");
        userWithOutPassword.setPhoneNumber("9846160222");
        userWithOutPassword.setEmail("vishnuprasad@mail.com");
        userWithOutPassword.setDateOfBirth(c);
        userWithOutPassword.setEmployeeNumber("7048");
        userWithOutPassword.setBloodGroup(BloodGroup.O_POS);
        userWithOutPassword.setGender(Gender.MALE);

        userWithOutPasswordList.add(userWithOutPassword);
        return userWithOutPasswordList;
    }

}