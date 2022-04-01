package com.user.userservice;

import com.user.userservice.model.User;
import com.user.userservice.repo.UserRepository;
import com.user.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

//	@Test
//	public void getAllUsersTest(){
////when(userRepository.findAll()).thenReturn(Stream.of(new User("1","Nikil","Ma","n","1234567890","22-04-2022","Male","12","o","dsfsdf@gmail.com","21123")).collect(Collectors.toList()));
//		when(repository.findAll()).thenReturn(Stream
//				.of(new User(376, "Danile", 31, "USA"), new User(958, "Huy", 35, "UK")).collect(Collectors.toList()));
//		assertEquals(2, userService.getAllUser().size());
//	}



	@Test
	void contextLoads() {
	}

}
